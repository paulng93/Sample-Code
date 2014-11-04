//******************************************************************************
//**  File: ChildGrep.cpp
//**  Date: October 2006
//**  Author: Kelvin Sung
//******************************************************************************
#include <sys/types.h>   // for fork, wait
#include <sys/wait.h>    // for wait
#include <unistd.h>      // for fork, pipe, dup, close
#include <stdio.h>       // for NULL, perror
#include <iostream>      // for cout
using namespace std;

//
// do   cat - | grep 
//
int main( int argc, char** argv ) {
    int fds[2];
    int pid;

    // create a pipe
    // fds[0] - is input for the pipe (we want to input from here)
    // fds[1] - is the output channel for the pipe (we want to output to here)
    if ( pipe( fds ) < 0 ) { perror( "pipe error" ); exit(1); }

    // fork a child
    if ( ( pid = fork( ) ) < 0 ) { perror( "fork error" ); exit(1); }
  
    if ( pid == 0 ) {
        // I'm a child
        // I wake up knowing my parent's stdin(0), stdout(1), and stderr(2)
        // I want to listen to my parent from the pipe, instead of from stdin
        dup2(fds[0], 0);    // we want pipe[0] to be the same as stdin(0)
        close(fds[1]);    // we don't want to do anything to pipe[1] (parent will)

        cout << "Child is about to turn itself into grep ... bye ...\n";
        execlp("grep", "grep", "fun", (char *) 0);

        // **** this part of the child process will _NEVER_ be reach! ***
        cerr << "This will NEVER appear, really!!";
    } else {
        // I'm a parent
        close(fds[0]);
        dup2(fds[1], 2);    // stderr(2) will now go into pipe[1]
        
        int c=0;
        char cmd[100];
        cmd[0] = '\0';

        cout << "Type something in ... \n";
        cout << "    if the pattern [fun] is typed, the whole string will be echoed by the child-grep\n";
        cout << "    type [quit] to quit (no! without the square-brackets!\n";
        cout << "    go ahead, try it ...\n";
        while (strcmp("quit", cmd) != 0) {
            cout << "Kelvin_Shell[" << c << "]: ";
            cin >> cmd;
            cerr << cmd << "\n";
            c++;
        }
        // when we quit, child will die .. 
        // because pipe will be broken and: eof will be read by the child
        cout << "all done playing, fun?" << endl;
  }
}
