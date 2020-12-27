# MessageApp
This app is a GUI based software program that allows users to send and receive information using internet connection.
The client connects to the server which is hosted by the Admin using his IP address and port number on which message packets will be send/ received.

### Features
- [x] Generated a graphical user interface for the client and server to ensure a smooth user experience by deploying textbox and buttons into the GUI layout.
- [x] Generated the server by hosting the servlet application on the local IP address and an empty port.
- [x] Implemented the client to connect with the server through the same IP address and port while ensuring to wait for the server to be live.
- [x] Ensured a secure chat application by using local IP addresses and ports.

### Notes
Description of any challenges encountered while building the application:
- Although I had come up with the idea of this application, I did not know how to implement it due to the lack of knowledge in servlets.
- I also did not know how to find IP addresses and ports and had to invest time into understanding these concepts.

## How To
### How to find IP address?
- Open Command prompt
- Type `ipconfig/all`
- Look for ipv4 address in your wifi section.

### How to find port numbers?
- Open Command prompt
- Type `netstat -a`
- Look for your IP address
- The port number will be listed next to the IP address.
