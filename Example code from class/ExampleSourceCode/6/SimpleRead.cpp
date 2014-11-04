//******************************************************************************
//**  File: SimpleRead.cpp
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
using namespace std;

//
// do 
//
int main( int argc, char** argv ) {
    int fd;
    int pid;
    char buf[10];
    int  size = 1;

    fd = open("./TestFile", O_RDONLY);

    while (size != 0) {
        size = read(fd, buf, 7);
        cout << "Buf=";
        for (int c=0; c<size; c++)
          cout << buf[c];
    }

    close(fd);
    return 0;
}
