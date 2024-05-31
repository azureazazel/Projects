// Server2 class that
// receives data and sends data

import java.io.*;
import java.net.*;

class Server {

	public static void main(String args[])
		throws Exception
	{

		// Create server Socket
		ServerSocket ss = new ServerSocket(5106);

		// connect it to client socket
		Socket s = ss.accept();
		System.out.println("Connection established");

		// to send data to the client
		DataOutputStream ps
			= new DataOutputStream(s.getOutputStream());

		// to read data coming from the client
		BufferedReader br
			= new BufferedReader(
				new InputStreamReader(
					s.getInputStream()));

		// to read data from the keyboard
		/*BufferedReader kb
			= new BufferedReader(
				new InputStreamReader(System.in));*/

		// server executes continuously
		while (true) {
			//str is used to identify whether to upload or to send file
			if(br.readLine().equals("1"))
			{
				//Sending file to client

				//open file
				String fileName = br.readLine();
				System.out.println(""+fileName);
				File file= new File("C:\\Users\\q\\Downloads\\CN_Project_files\\"+fileName+".pptx");

				if(file.exists())
				{
					System.out.println("exists");
					ps.writeBytes("1"+"\n");

					FileInputStream fin= new FileInputStream(file);

					//send file to client
					int ch;
					byte arr[]=new byte[1000];
					while((ch=fin.read(arr))!=-1)
					{
						ps.write(arr,0,ch);
					}
				}
				else
				{
					ps.writeBytes("0"+"\n");
				}
			}
			else
			{
				//receiving file from client


				
				//create file
				String fileName = br.readLine();
				byte buffer[] = new byte[1000];
				String path= "C:\\Users\\q\\Downloads\\CN_Project_files\\";
				File myFile = new File(path+"new"+fileName+".pptx");

				//recieve file from client and write to newly created file
				FileOutputStream fout = new FileOutputStream(myFile);
				int valid;
				while((valid = s.getInputStream().read(buffer))>-1)
				{
					fout.write(buffer,0,valid);
				}
				fout.close();
			}
				// close connection
				ps.close();
				br.close();
				//kb.close();
			ss.close();
			s.close();

			// terminate application
			System.exit(0);

		} // end of while
	}
}
