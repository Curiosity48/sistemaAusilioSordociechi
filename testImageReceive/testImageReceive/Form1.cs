using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;

namespace testImageReceive
{
    public partial class Form1 : Form
    {
        private MyTcpListener tcp;
        private Thread t;

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            button1.Enabled = false;
            tcp = new MyTcpListener(pictureBox1);
            t = new Thread(new ThreadStart(tcp.ReceiveImg));
            tcp.Connect();
            t.Start();
        }
    }
}
