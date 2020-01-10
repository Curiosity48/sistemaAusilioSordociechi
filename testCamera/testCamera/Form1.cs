using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace testCamera
{
    public partial class Form1 : Form
    {
        private Webcam cam;

        public Form1()
        {
            InitializeComponent();
            cam = new Webcam(pictureBox1, comboBox);
            button2.Enabled = false;
            button3.Enabled = false;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            cam.Load();
        }

        private void Button1_Click(object sender, EventArgs e)
        {
            cam.Start();
            button1.Enabled = false;
            button2.Enabled = true;
            button3.Enabled = true;
        }

        private void Button2_Click(object sender, EventArgs e)
        {
            cam.Stop();
            button1.Enabled = true;
            button2.Enabled = false;
            button3.Enabled = false;
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            cam.Stop();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (cam.broadcast)
            {
                cam.Disconnect();
                button3.Text = "Start broadcast";
            }
            else
            {
                cam.Connect();
                button3.Text = "Stop broadcast";
            }
        }
    }
}
