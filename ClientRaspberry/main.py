import cv2
import MyTcpClient

cv2.namedWindow("Original")
vc = cv2.VideoCapture(0)
sock = MyTcpClient()

if vc.isOpened():   # try to get the first frame
    rval, frame = vc.read()
else:
    rval = False

while rval:
    # encval, tupleImg = cv2.imencode(".png", frame)  # encoding image in byte array to send it over tcp
    # listImg = list(tupleImg)
    # frame2 = cv2.imdecode(tupleImg, 1)    # decoding byte array to image (test)

    cv2.imshow("Original", frame)
    rval, frame = vc.read()
    key = cv2.waitKey(10)
    if key == 27:   # exit on ESC
        break

cv2.destroyWindow("preview")
vc.release()
