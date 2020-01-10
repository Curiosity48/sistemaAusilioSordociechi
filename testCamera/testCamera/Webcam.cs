using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using AForge.Video;
using AForge.Video.DirectShow;

namespace testCamera
{
    class Webcam
    {
        private MyTcp client;
        private FilterInfoCollection videoDevices;
        private VideoCaptureDevice finalVideo;
        private ComboBox comboBox;
        private PictureBox pictureBox;
        public bool broadcast { get; set; }

        public Webcam(PictureBox pictureBox, ComboBox comboBox)
        {
            videoDevices = new FilterInfoCollection(FilterCategory.VideoInputDevice);
            finalVideo = new VideoCaptureDevice();
            this.comboBox = comboBox;
            this.pictureBox = pictureBox;
            broadcast = false;
        }

        public void Load()
        {
            //ricerca dispositivi video
            foreach (FilterInfo VideoCaptureDevice in videoDevices)
            {
                comboBox.Items.Add(VideoCaptureDevice.Name);
            }
            comboBox.SelectedIndex = 0;
        }

        public void Start()
        {
            finalVideo = new VideoCaptureDevice(videoDevices[comboBox.SelectedIndex].MonikerString);
            finalVideo.NewFrame += FinalVideo_NewFrame;
            finalVideo.Start();
        }

        private void FinalVideo_NewFrame(object sender, NewFrameEventArgs eventArgs)
        {
            //acquisizione frame
            Bitmap video = (Bitmap)eventArgs.Frame.Clone();
            video.SetResolution(576, 768);
            pictureBox.Image = video;

            //spedizione su rete locale (risoluzione originale)
            Bitmap clone = (Bitmap)eventArgs.Frame.Clone();
            clone.SetResolution(576, 768);
            if (broadcast)
            {
                SendImage(video);
            }           
        }

        private void SendImage(Bitmap img)
        {
            client.Invia(img);
        }

        public void Stop()
        {
            if (finalVideo.IsRunning == true) finalVideo.Stop();
            pictureBox.Image = null;
            client.Close();
        }

        public void Connect()
        {
            client = new MyTcp("localhost", 5000);
            broadcast = true;
        }

        public void Disconnect()
        {
            broadcast = false;
            client.Close();
        }
    }
}
