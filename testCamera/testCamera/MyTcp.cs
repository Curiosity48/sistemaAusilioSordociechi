using System;
using System.Drawing;
using System.Net.Sockets;
using System.IO;
using System.Threading;

namespace testCamera
{
    class MyTcp
    {
        private TcpClient client;
        private NetworkStream stream;
        private Byte[] data;
        private MemoryStream ms;

        public MyTcp(string ip, int porta)
        {
            ms = new MemoryStream();
            client = new TcpClient(ip, porta);
            stream = client.GetStream();
        }

        public void Invia(Bitmap img)
        {
            if (this.IsConnected())
            {
                //conversione immagine in byte[]
                img.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);
                img.Dispose();
                ms.Flush();
                ms.Close();
                data = ms.ToArray();

                //invio grandezza del vettore di byte
                int size = data.Length;
                byte[] tmp = BitConverter.GetBytes(size);
                stream.Write(tmp, 0, tmp.Length);

                //invio immagine in formato byte[]
                Thread.Sleep(100);
                stream.Write(data, 0, data.Length);
            }
            
        }

        public void Close()
        {
            if (this.IsConnected())
            {
                stream.Close();
                client.Close();
                ms.Close();
            }
            
        }

        public bool IsConnected()
        {
            if(client != null)
                return client.Connected;
            else
                return false;
        }
    }
}
