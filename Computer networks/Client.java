// Client2 class that
// sends data and receives also

import java.io.*;
import java.net.*;

//import javax.lang.model.util.ElementScanner14;

class Client {

	public static void main(String args[])
		throws Exception
	{
		// to read data from the keyboard
		BufferedReader keyboard
			= new BufferedReader(
				new InputStreamReader(System.in));

		String str=keyboard.readLine();
		String str2[]=str.split(" ");
		int port = Integer.parseInt(str2[1]);

		// Create client socket
		Socket s = new Socket("localhost", port);

		// to send data to the server
		
		DataOutputStream dos
			= new DataOutputStream(
				s.getOutputStream());

		BufferedReader br
		= new BufferedReader(
			new InputStreamReader(
				s.getInputStream()));


		System.out.println("enter getfile to get data from server or uploadfile to send data");

		// repeat as long as exit
		// is not typed at client
		str=keyboard.readLine();
		String str3[]=str.split("<");

		if (str3[0].equals("get")) {

			// recieving file from server 


			dos.writeBytes("1"+"\n");
			// receieve from server

			//create byte array
			String fileName = str3[1].substring(0,str3[1].length()-1);
			dos.writeBytes(fileName+"\n");

			if(br.readLine().equals("1"))
			{
			byte buffer[] = new byte[1000];
			String path= "C:\\Users\\q\\Downloads\\CN_Project_files\\";

			//create new file
			File myFile = new File(path+"new"+fileName+".pptx");

				//recieve from server and write to newly created file
				FileOutputStream fout = new FileOutputStream(myFile);
				int valid;
				while((valid = s.getInputStream().read(buffer))>-1)
				{
					fout.write(buffer,0,valid);
				}
				fout.close();
			}
			else
			{
				System.out.println("file does no exist");
			}
		}
		else
		{

			// uploading file to server
			if(str3[0].equals("upload"))
			{

			
				dos.writeBytes("0"+"\n");

				//read file
				String fileName = str3[1].substring(0,str3[1].length()-1);
				dos.writeBytes(fileName+"\n");
				File file= new File("C:\\Users\\q\\Downloads\\CN_Project_files\\"+fileName+".pptx");

				if(file.exists())
				{
					FileInputStream fin= new FileInputStream(file);

					//send file to server
					int ch;
					byte arr[]=new byte[1000];
					while((ch=fin.read(arr))!=-1)
					{
						dos.write(arr,0,ch);
					}
					fin.close();
				}
				else
				{
					System.out.println("file does not exist");
				}
			}
			else
			{
				System.out.println("Entered wrng command");
			}
		}
		// close connection.
		dos.close();
		//br.close();
		keyboard.close();
		s.close();
	}
}
