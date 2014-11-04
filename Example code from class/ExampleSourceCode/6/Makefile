CC = g++

all: SimpleRead SimpleWrite ParentChildShare

SimpleRead: SimpleRead.o
	g++ -o SimpleRead $@.o

SimpleWrite: SimpleWrite.o
	g++ -o SimpleWrite $@.o


ParentChildShare: ParentChildShare.o
	g++ -o ParentChildShare $@.o

clean: 
	rm -f *.o	ParentChildShare SimpleWrite SimpleRead *~
