//******************************************************************************
//**  File: ParentChildShare.cpp
//**  Date: Nov 2006
//**  Author: Kelvin Sung
//******************************************************************************
#include <sys/types.h>   // for fork, wait
#include <sys/wait.h>    // for wait
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>      // for fork, pipe, dup, close
#include <stdio.h>       // for NULL, perror
#include <iostream>      // for cout
#include <stdlib.h>
using namespace std;

char *GrandChildMsg = "                           From Grand Child:";
char *ChildMsg = "   From Child:";


void PrintBuf(char* buf, int size, char* msg) 
{
    cout << msg << " (" << size << "):";
    for (int c=0; c<size; c++)
        cout << buf[c];
}

//
// 
//

int main( int argc, char** argv ) {
    int fd;
    int pid;

    fd = open("./TestFile", O_RDONLY);

    pid = fork();

    if (pid == 0) {

        char *msg;
        pid = fork();

        if (pid == 0) {
            // grand child
            msg = GrandChildMsg;
            close(fd);
            fd = open("./TestFile", O_RDONLY);
        } else {
            // child
            msg = ChildMsg;
        }
        char buf[10];
        int  size = 1;
        while (size != 0) {
            size = read(fd, buf, 7);
            PrintBuf(buf, size, msg);
            sleep(1);
        }
        cout << msg << "ends ...\n";
        close(fd);

    } else {
        // parent
        int opt = 0;
        cerr << "From parent: [0-start, 1-random pos, 2-quit] ";
        while  (opt != 2 ) {
            cin >> opt;

            switch (opt) {

                case 0:
                  cerr << "From Parent: start to start\n";
                  lseek(fd, 0, SEEK_SET);
                  break;

                case 1:
                  {
                    int off = rand();
                    off = (off&0XFF) * 7;
                    cerr << "From Parent: set to random: " << off << endl;
                    lseek(fd, off, SEEK_SET);
                  }
                  break;

                 case 2:
                  cerr << "From Parent: set to end\n";
                  lseek(fd, 0, SEEK_END);
                  break;
            }
        }
        close(fd);

        wait(NULL);
    }


  return 0;
}
