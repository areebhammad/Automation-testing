import sys #sys module has to read the command line arguments
args =sys.argv
sum=0
for i in range (1, len (args)):
    sum+=int(args[i])
print (sum)

