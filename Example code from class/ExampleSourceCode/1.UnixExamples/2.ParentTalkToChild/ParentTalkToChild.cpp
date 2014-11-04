//******************************************************************************
//**  File: ParentTalkToChild.cpp
//**  Date: October 2006
//**  Author: Kelvin Sung
//******************************************************************************
#include <sys/types.h>   // for fork, wait
#include <sys/wait.h>    // for wait
#include <unistd.h>      // for fork, pipe, dup, close
#include <stdio.h>       // for NULL, perror
#include <iostream>      // for cout
using namespace std;

int main( int argc, char** argv ) {
      int fds[2];
      int pid;

    // create a pipe
    // fds[0] - is input for the pipe (we should listen to this)
    // fds[1] - is the output channel for the pipe (we should output to this)
    if ( pipe( fds ) < 0 ) { perror( "pipe error" ); exit(1); }

    cerr << "Pipe fds contents: " << fds[0] << " " << fds[1] << "\n";

    // fork a child
    if ( ( pid = fork( ) ) < 0 ) { perror( "fork error" ); exit(1); }

    if ( pid == 0 ) {
        // I'm a child
        // I wake up knowing my parent's stdin(0), stdout(1), and stderr(2)
        // I want to listen to my parent from the pipe, instead of from stdin
        dup2(fds[0], 0);    // I want pipe[0] to be the same as stdin(0)
        close(fds[1]);        // I don't want to do anything to pipe[1] (parent will)

        int w=1;
        while (w != 0) {
            cin >> w;    // from stdin, but we have changed this to be from pipe(parent)!
            cerr << "From Child: w=" << w << "\n";
        }
        cerr << "Child got w=0 ... quiting ...\n";

     } else {
        // I'm a parent
        close(fds[0]);
        dup2(fds[1], 2);    // stderr(2) will now go into pipe[1]
        cout << "OK, this is parent, here is the rule:\n";
        cout << "    0   - to kill the child\n";
        cout << "    1-5 - to pass to the child\n";
        cout << "    >5 -  to parent only \n";
        int n;
        do {
            cout << "Enter a number: ";
            cin >> n;
            if (n <= 5) {
                   cout << "Entered: " << n << " passing to child\n";     // to console
                   cerr << n << "\n";    // to child
            } else {
                   cout << "Entered: " << n << " this is parent only!\n";
            }
        } while (n != 0);

        // child should be dead by now
        wait( NULL );
        cout << "all done playing, fun?" << endl;
     }
}