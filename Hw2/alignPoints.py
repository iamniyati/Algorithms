"""
Filename: alignPoints.py

Authors: Rudresh Pandit(rmp7494), Niyati Shah(nxs6032)

Program to find a line on a plane surface which will have maximum pair of points
aligned when folded
"""
def formLines(x,y):
    """
    Function to form lines from one point to every other point
    :param x:   list of all point's X coordinate
    :param y:   list of all point's Y coordinate
    :return:    equation of line(Y=Mx+C) in terms of M,C and the midpoints
    """
    m  = []
    c = []
    mpx = []
    mpy = []
    for i in range(len(x)):
        for j in range(i+1,len(y)):
            if (x[j]-x[i]) == 0:
                slope = 'inf'
                C = x[i]
            else:
                slope = (y[j]-y[i])/(x[j]-x[i])
                C = y[i] - (x[i]*(slope))
            m.append(slope)
            c.append(C)
            mx = (x[i] + x[j])/2
            my = (y[i] + y[j])/2
            mpx.append(mx)
            mpy.append(my)
    return m,c,mpx,mpy

def findAlign(m,mpx,mpy):
    """
    Function to find all the lines parallel to a segment and if both of them
    have the same perpendicular bisector
    :param m:   all the slopes
    :param mpx: list of all the x coordinates of the midpoints
    :param mpy: list of all the y coordinates of the midpoints
    :return:    a list of all pair of points formed for each segment
    """
    count = []
    for k in range(len(m)):
        count.append(1)
    for i in range(len(m)):
        for j in range(i+1,len(m)):
            if m[i]==m[j]:  #slopes do match(add slope case for inf)
                if m[i] == 0:
                    if mpx[i]==mpx[j]:
                        count[i] = count[i] + 1
                else:
                    if m[i] == 'inf':
                        newslope = 0
                    else:
                        newslope = -1*(1/m[i])
                    newC = mpy[i] - (newslope*mpx[i])
                    #now check if its a perpendicular bisector
                    rhs = newslope*mpx[j] + newC
                    if mpy[j]==rhs:
                        count[i] = count[i] + 1
    return count

def findMax(count):
    """
    Function to find the max in the given list
    :param count:   list of all occurences of a particular segment
    :return:    max of these occurances
    """
    max = 1
    for i in range(len(count)):
        if count[i]>max:
            max = count[i]
    return max


def main():
    x_coord = []
    y_coord = []
    no_of_coordinates = int(input())
    for i in range(no_of_coordinates):
        line = input()
        l = line.split(" ")
        x_coord.append(int(l[0]))   #filling up the x and y coordinates
        y_coord.append(int(l[1]))
    #print(x_coord)
    #print(y_coord)


    m = []
    c = []
    mpx = []
    mpy = []
    m,c,mpx,mpy = formLines(x_coord,y_coord)
    count = []
    count = findAlign(m,mpx,mpy)
    max = findMax(count)
    print(max)

main()
