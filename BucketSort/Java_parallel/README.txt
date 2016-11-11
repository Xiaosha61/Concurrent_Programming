## Compile 
1. enter BucketSort/Java_parallel directory.
2. $ mkdir bin
3. $ cd src/
4. $ javac App.java -d ../bin

##  How to start this program   

1. Enter the directory: BucketSort/Java_parallel.
2. And there are 4 folders:
	bin - stores all .class files
	src - stores all .java source codes
	input - stores 2 testing data input files(which are both from your slides)
		  - medium.in is the large file
		  - input1.txt is the small one, and you could create more testing files in this folder.
	output - I used this folder to store all output sorted results here.
		   - bucketsort.out can be the name of the sorted medium.in(large one);
		   - input1.txt could be the output sorted input1.txt
3. Then you can go into class file "/bin" and type in this command to run the program(App.java is the main class):
			cd bin
			java App
4. Then you could test it following the prompts.

/************************  After starting the program  ****************************/

1. First you need to type in the input file's name:
			../input/medium.in
2. Secondly you need to type in the output file's name:
			../output/bucketsort.out
3. Then you could decide how many threads you need(1/2/4/8).
4. Finally enter and run it.
5. After running that you can check the output in the file "~/xiaosha/BucketSort/output".

/*********  Here is the example with testing data showing in slides **************/

+-------------------------------------------------------+
|student01@fcdsrl08:~/xiaosha/BucketSort/bin$ java App  |
|Please type in the input file :                        |
|../input/medium.in                                     |
|The output file should be :                            |
|../output/bucketsort.out                               |
|How many threads do you want this time?                |
|4                                                      |
|Computing needs :  568 ms                              |
|student01@fcdsrl08:~/xiaosha/BucketSort/bin$           |
+-------------------------------------------------------+

