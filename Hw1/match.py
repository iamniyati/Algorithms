"""
FileName: match.py

Authors:  Niyati Shah(nxs6032)

Program to determine more than one stable matching for the given input
"""
def getMan(pairing, y):
    """
    Function to find the current paired man for a woman
    :param pairing: Dictionary which has pairs
    :param y: woman for which man has to be found
    :return: man
    """
    for m,w in pairing.items():
        if w == y:
            return m


def isPairPossible(woman,x,y,pairing):
    """

    :param woman: women table
    :param x: man attempting to form a pair
    :param y: potential woman for the man
    :param pairing: dictionary which has pairs
    :return: yes if man can form pair with woman
    """

    theMan = getMan(pairing,y)      #function call
    if theMan == None:
        return False
    theChoice = woman[y]        #getting choices of men of the woman y
    for ma in theChoice:        #return true if x has high rank than theMan

        if ma == theMan:
            return False
        elif ma == x:
            return True


def stable_matching(man,woman):
    """
    Function to find the stable matching for the given input
    :param man: Input table of men
    :param woman: Input table of women
    :return: dictionary with stable pairs
    """
    man_free = []
    woman_free = []
    pairing = {}
    n = len(man[0])
    for i in range(n):      #populate the list of free men and women
        man_free.append(i)
        woman_free.append(i)
    while len(man_free)>0:  #condition for the function to end
        x = man_free.pop(0)
        choices = man[x]
        for wo in choices:
            y = wo
            if y in woman_free: # if women is free, form the pair
                woman_free.remove(y)
                pairing[x] = y
                break
            else:
                if isPairPossible(woman,x,y,pairing):   #if not free then is pair possible
                    theMan = getMan(pairing,y)
                    if theMan is not None:
                        del(pairing[theMan])    #delete old pair
                        man_free.append(theMan) #add the old man to free_man list
                        pairing[x] = y          #form the new pair
                    break
    return pairing


def main():
    paired = {}         #dictionary to store pairs
    reversepair = {}    #dictionary to store pairs when we swap
    manInput = []       #Input for men
    womenInput = []     #Input for women
    n = int(input())
    for i in range(n):
        row = input()
        row = row.split(" ")
        intRow = []
        for x in row:
            intRow.append(int(x))
        manInput.append(intRow)
    for i in range(n):
        row = input()
        row = row.split(" ")
        intRow = []
        for x in row:
            intRow.append(int(x))
        womenInput.append(intRow)

    paired = stable_matching(manInput,womenInput)
    reversepair = stable_matching(womenInput,manInput)
    rev = dict((w,m)for m,w in reversepair.items())     #reversing the dictionary

    flag = True
    for m,w in paired.items():
        if rev[m] is not w:     #to check if there is more than one stable pairs
            flag = False
    if flag is True:
        print('NO')
    else:
        print('YES')

main()
