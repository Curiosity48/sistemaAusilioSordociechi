print("Client started")
import cv2
from MyTcpClient import MyTcpClient
import time

cv2.namedWindow("Client")
vc = cv2.VideoCapture(0)
sock = MyTcpClient()

# resize frames captured by camera
def make_480p():
    vc.set(3, 640)
    vc.set(4, 480)

def make_720p():
    vc.set(3, 1280)
    vc.set(4, 720)

def make_1080p():
    vc.set(3, 1920)
    vc.set(4, 1080)


if vc.isOpened():   # if any webcam is connected
    rval = True
    sock.connect("localhost", 5000)
    make_480p()
else:
    rval = False

while rval:
    rval, frame = vc.read()     # read frame
    cv2.imshow("Client", frame)   # show it
    encval, array = cv2.imencode(".bmp", frame)  # encode to array
    length = len(array)

    sock.send(length.to_bytes(5, byteorder='big'))   # send length
    time.sleep(0.01)
    sock.send(array)    # send array
    key = cv2.waitKey(10)   # press a key to exit
    if key != -1:
        break

sock.close()
cv2.destroyWindow("Client")
vc.release()
