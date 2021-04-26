package com.javarevision;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MuiltServer {
 private int  check=0;
 private int check23=90;
    public static void main(String[] args) {
        // write your code here
        new MuiltServer();
    }
	public MuiltServer(){
        try {
        ServerSocket serverSocket=new ServerSocket(4999);


int client=1;
        while(true){
            Socket socket=serverSocket.accept();
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("Client is Connected");
            System.out.println("The IP Address of Client "+client+"is:-"+inetAddress.getHostAddress());
            System.out.println("The Host name is:-"+inetAddress.getHostName());
            HandleAClient task = new HandleAClient(socket);
            new Thread(task).start();
            client++;



        }




    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    class HandleAClient implements Runnable{
        private  Socket socket;

     public HandleAClient(Socket socket){
             this.socket=socket;
             

        }

        /**
         * When an object implementing interface {@code Runnable} is used
         * to create a thread, starting the thread causes the object's
         * {@code run} method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method {@code run} is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {

            Double raduis=0.00;
            Double leng=0.00;


            try {
                DataInputStream getdata = new DataInputStream(socket.getInputStream());
                DataOutputStream passData=new DataOutputStream(socket.getOutputStream());

                while(true){
                    raduis=getdata.readDouble();
                    leng=getdata.readDouble();
                    System.out.println("The Received  radius from client  is"+raduis);
                    System.out.println("The Received  Leng from client is "+leng);
                    raduis=raduis+raduis;
                    leng=leng+10;

                    passData.writeDouble(raduis);
                    passData.writeDouble(leng);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
