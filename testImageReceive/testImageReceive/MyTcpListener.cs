using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Sockets;
using System.Net;
using System.Drawing;
using System.Windows.Forms;
using System.IO;

namespace testImageReceive
{
    class MyTcpListener
    {
        private TcpClient client;
        private TcpListener server;
        private NetworkStream stream;
        private byte[] data;
        private PictureBox box;

        public MyTcpListener(PictureBox box)
        {
            this.box = box;
            Int32 porta = 5000;
            IPAddress address = IPAddress.Parse("127.0.0.1");
            server = new TcpListener(address, porta);
            server.Start();
            data = new byte[1024];
        }

        public void Connect()
        {
            //inizializzazione connessione
            client = server.AcceptTcpClient();
            stream = client.GetStream();
        }

        public void ReceiveImg()
        {
            //la connessione finisce quando il client la chiude
            while (client.Connected)
            {
                //ricezione dimensione immagine in byte[] e riallocazione vettore
                stream.Read(data, 0, data.Length);    //num celle occupate di data (in data è contenuto la grandezza in byte dell'immagine)
                int dim = BitConverter.ToInt32(data, 0);
                data = new byte[dim];

                //ricezione immagine in byte[] e conversione
                stream.Read(data, 0, data.Length);    //adesso in data è contenuta l'immagine in formato byte
                Image finalImg = (Image)((new ImageConverter()).ConvertFrom(data));
                System.IO.File.WriteAllBytes("C:\\test\\tmp.jpg", data);
            }
            Close();
        }

        public void Close()
        {
            stream.Close();
            client.Close();
            server.Stop();
        }
    }
}
