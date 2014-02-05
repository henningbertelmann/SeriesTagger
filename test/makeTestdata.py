#!/usr/bin/python

# usage: ./makeTestData.py path/to/folder

import os
import sys
import re

def main(directory):
  base =os.path.basename(directory)
  basecopy = base + "_copy"
  print basecopy
  copyFolder = os.path.join(os.path.dirname(directory), basecopy)
  print copyFolder
  try:
    os.mkdir(copyFolder)
  except OSError:
    print "Erst test loeschen bitte"
    return
  for (dirpath, dirnames, filenames) in os.walk(directory):
    newdir=re.sub(base, base + "_copy", dirpath)
    if(dirpath != directory): 
      os.mkdir(newdir)
    for f in filenames:
      open(os.path.join(newdir,f), 'a').close()  

if __name__ == "__main__":
  if ( len(sys.argv) != 2 or (not os.path.isdir(sys.argv[1]))):
    print "Ordner als Arugumebt angeben"
  else:
   main(sys.argv[1])      

