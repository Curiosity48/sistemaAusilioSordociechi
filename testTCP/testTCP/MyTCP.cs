using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;

namespace testTCP
{
    class MyTCP
    {
        private TcpClient client;
        private Byte[] data;
        private NetworkStream stream;

        public MyTCP()
        {
            avviaConnessione();
        }

        public MyTCP(string server, Int32 porta)
        {
            avviaConnessione(server, porta);
        }

        private void avviaConnessione()
        {
            client = new TcpClient("localhost",  1234);
            stream = client.GetStream();
            data = new Byte[256];
        }

        private void avviaConnessione(string server, Int32 porta)
        {
            client = new TcpClient(server, porta);
            stream = client.GetStream();
            data = new Byte[256];
        }

        public void spedisciString(string messaggio)
        {
            data = System.Text.Encoding.ASCII.GetBytes(messaggio);
            stream.Write(data, 0, data.Length);
        }

        public void SpedisciBinario(Byte[] b)
        {
            data = b;
            stream.Write(data, 0, data.Length);
        }
    }
}
