# A mini project in Java: Basic image processing applications along with text extraction facility for Aadhar card images.
There are 4 basic image processing applications and an OCR application, where each of them has been implemented as client and server model.
The client goes through different options available and requests for an application, the data required is collected at the client side and sent to server where actual processing takes place, the final result is again sent to the client side. The server can handle more than one client, as it is multi-threaded.

Implemented by: Bobbili Veerendra Raj Kumar - 19005
This application was developed as a coursework project while pursuing my Master of Science in Mathematics at SSSIHL.

## Folders:
* main directory is the source directory.
	* main directory contains server, client and imglib(imageLibrary-> this should not be deleted as it is required by Cleint and Server programs.) directories.
	* server directory contains clientHandler and utility directories and Server class.
	* clientHandler directory contains Serve class which handles or serve the client in a new thread.
	* utility directory contains GrayScale, MirrorImage, Negative, OCR, RandomImage classes which are actually the services Server class provides.
	* client directory contains utility directory and Client class
	* utility directory contains ScrollablePicture, ScrollPicture, Serivce classes which help the Client class to handle the client.
<!-- * packages folder contains the zip file which needs to be installed in your local system (this is used by OCR class.) -->
* The souce code relies on the Tessract package **Tess4J-3.4.8-src**, therefore it must be installed separately(this is used by OCR class).

## Instructions:
* tessdata folder needs to be replaced from Tess4J-3.4.8-src folder for OCR class functionality.
* A **JARPATH** file must be created, which contains all the paths to required packages and jar files.
	* For example: The file start with `export MYJARPATH=/...pathto../Tess4J-3.4.8-src/Tess4J/lib:`, where `pathto` contains the path from `/home` directory to the package, and must contain path to all the jarfiles that is used by the Tesseract package, each separted by **:**, this **JARPATH** must be provided while complication of the source code for error free execution.
## Note:
* OCR class uses Tess4J package to achieve its functionality.
* All other classes use inbuilt java libraries to achieve their functionality.
