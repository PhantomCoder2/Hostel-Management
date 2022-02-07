package Proj;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/home")
public class home extends HttpServlet {
	class Room {
		String roomID;
		String roomType;
		String roomCharge;
		String roomLocation;
		String roomStatus;
		String paymentStatus;
	}
	class Student {
		String email;
		String password;
	}
	ArrayList<Student> students;
	ArrayList<Room> availableRooms;
	ArrayList<Room> checkedRooms;
	ArrayList<Room> allRooms;
	public home() {
		super();
		availableRooms = new ArrayList<Room>();
		checkedRooms = new ArrayList<Room>();
		allRooms = new ArrayList<Room>();
		students = new ArrayList<Student>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email1 = request.getParameter("email");
		String password1 = request.getParameter("password");
		String checkIn = request.getParameter("checkin");
		String checkOut = request.getParameter("checkout");
		String checkInID = request.getParameter("checkinid");
		String checkOutID = request.getParameter("checkoutid");
		String signUp = request.getParameter("signup");
		String login = request.getParameter("login");
		
		String roomID = request.getParameter("roomID");
		String roomL = request.getParameter("roomLocation");
		String roomT = request.getParameter("roomType");
		String roomC = request.getParameter("roomCharge");
		String delID = request.getParameter("delID");
		
		PrintWriter out = response.getWriter();
		
		
		if((email1!=null && password1!=null && email1.equals("admin@gmail.com") && password1.equals("admin")) || roomID!=null || delID!=null) {
			if(roomID != null) {
				for(int i=0; i<allRooms.size(); i++) {
					if(allRooms.get(i).roomID.equals(roomID)) {
						allRooms.remove(i);
					}
				}
				for(int i=0; i<availableRooms.size(); i++) {
					if(availableRooms.get(i).roomID.equals(roomID)) {
						availableRooms.remove(i);
					}
				}
				Room obj = new Room();
				obj.roomID = roomID;
				obj.roomType = roomT;
				obj.roomCharge = roomC;
				obj.roomLocation = roomL;
				obj.roomStatus = "Available";
				obj.paymentStatus = "Not Paid";
				availableRooms.add(obj);
				allRooms.add(obj);
			}
			if(delID != null) {
				ArrayList<Room> newrooms = new ArrayList<Room>();
				for(int i=0; i<allRooms.size(); i++) {
					if(!allRooms.get(i).roomID.equals(delID)) {
						newrooms.add(allRooms.get(i));
					}
				}
				availableRooms = newrooms;
				allRooms = newrooms;
			}
			String s = "";
			for(int i=0; i<allRooms.size(); i++) {
				s+="<tr><th scope='row'>";
				s+=allRooms.get(i).roomID + "</th><td>";
				s+=allRooms.get(i).roomType + "</td><td>";
				s+=allRooms.get(i).roomLocation + "</td><td>";
				s+=allRooms.get(i).roomCharge + "</td>";
				s+="<td>" + allRooms.get(i).roomStatus + "</td>\r\n"
						+ "              <td>" + allRooms.get(i).paymentStatus + "</td>\r\n"
						+ "              <td><a href=\"update.html\" class=\"btn btn-primary\" data-toggle='modal' data-target='#exampleModal2'>Update</a></td>\r\n"
						+ "              <td><a href=\"home?delID=" + allRooms.get(i).roomID + "\" >Delete</a></td>";
			}
//for room creation request as Home?roomID=1&roomCharge=1 etc..
//for room update same as creation just keep the roomID same as which room needs to be updated
//for room deletion request will be Home?delID={id of the room you want to delete}
			String finalOut = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Hostel Management</title>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"css/admin.css\">\r\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x\" crossorigin=\"anonymous\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300&display=swap\" rel=\"stylesheet\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" integrity=\"sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n"
					+ "        <div class=\"container-fluid\">\r\n"
					+ "          <div class=\"collapse navbar-collapse\">\r\n"
					+ "            <ul class=\"navbar-nav\">\r\n"
					+ "              <li class=\"nav-item\">\r\n"
					+ "                <a class=\"nav-link\" aria-current=\"page\" href=\"CreateRoom.html\">Insert</a>\r\n"
					+ "              </li>\r\n"
					+ "              <li class=\"nav-item\">\r\n"
					+ "                <a class=\"nav-link\" href=\"index.html\">LOGOUT</a>\r\n"
					+ "              </li>\r\n"
					+ "            </ul>\r\n"
					+ "          </div>\r\n"
					+ "        </div>\r\n"
					+ "      </nav>\r\n"
					+ "      <div class=\"bg\">\r\n"
					+ "            <table>\r\n"
					+ "                <tr>\r\n"
					+ "                    <th>#Id</th>\r\n"
					+ "                    <th>Type</th>\r\n"
					+ "                    <th>Location</th>\r\n"
					+ "                    <th>Charge</th>\r\n"
					+ "                    <th>Room Status</th>\r\n"
					+ "                    <th>Payment Status</th>\r\n"
					+ "                    <th>Update</th>\r\n"
					+ "                    <th>Delete</th>\r\n"
					+ "                </tr>\r\n"
					+ "                <tr>\r\n"
					+                     s
					+ "                </tr>    \r\n"
					+ "            </table>\r\n"
					+ "      </div>\r\n"
					+ "</body>\r\n"
					+ "</html>";
			out.println(finalOut);
		}
		else if((email1!=null && password1!=null) || checkInID!=null || checkOutID!=null || checkIn!=null || checkOut!=null || signUp!=null || login!=null) {
			if(signUp != null) {
				if(email1!=null && password1!=null) {
					Student obj = new Student();
					obj.email = email1;
					obj.password = password1;
					students.add(obj);
					System.out.println("SINGUP SUCCESS");

					System.out.println(email1);
					System.out.println(password1);
				}else {
					System.out.println("SINGUP FAILED");
					response.sendRedirect("index.html");
				}
			}
			if(login != null) {
				int flag = 0;
				for(int i=0; i<students.size(); i++) {
					if(students.get(i).email.equals(email1) && students.get(i).password.equals(password1)) {
						flag = 1;
					}
				}
				if(flag == 0) {
					System.out.println("LOGIN FAILED");
					response.sendRedirect("index.html");
				}else {
					System.out.println("LOGIN SUCCESS");
				}
			}
			if(checkInID != null) {
				for(int i=0; i<availableRooms.size(); i++) {
					if(availableRooms.get(i).roomID.equals(checkInID)) {
						checkedRooms.add(availableRooms.get(i));
						availableRooms.remove(i);
					}
				}
			}
			if(checkOutID != null) {
				for(int i=0; i<checkedRooms.size(); i++) {
					if(checkedRooms.get(i).roomID.equals(checkOutID)) {
						availableRooms.add(checkedRooms.get(i));
						checkedRooms.remove(i);
					}
				}
			}
			if(email1 != null || checkOut != null) {
				String s1 = "";
				for(int i=0; i<checkedRooms.size(); i++) {
					s1+="<tr><th scope='row'>";
					s1+=checkedRooms.get(i).roomID + "</th><td>";
					s1+=checkedRooms.get(i).roomType + "</td><td>";
					s1+=checkedRooms.get(i).roomLocation + "</td><td>";
					s1+=checkedRooms.get(i).roomCharge + "</td>";
					s1+="<td><a href=\"home?checkout=1&checkoutid=" + checkedRooms.get(i).roomID + "\" class=\"btn btn-primary\">Checkout</a></td></tr>";
				}
// on logout href to login page
// on pressing check out button href to Home?checkout=1
				String s = "<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Hostel Management</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"css/student.css\">\r\n"
						+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x\" crossorigin=\"anonymous\">\r\n"
						+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n"
						+ "    <link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300&display=swap\" rel=\"stylesheet\">\r\n"
						+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" integrity=\"sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n"
						+ "        <div class=\"container-fluid\">\r\n"
						+ "          <div class=\"collapse navbar-collapse\">\r\n"
						+ "            <ul class=\"navbar-nav\">\r\n"
						+ "              <li class=\"nav-item\">\r\n"
						+ "                <a class=\"nav-link\" aria-current=\"page\" href=\"home?checkin=1\">CheckIn</a>\r\n"
						+ "              </li>\r\n"
						+ "              <li class=\"nav-item\">\r\n"
						+ "                <a class=\"nav-link\" href=\"index.html\">Log Out</a>\r\n"
						+ "              </li>\r\n"
						+ "            </ul>\r\n"
						+ "          </div>\r\n"
						+ "        </div>\r\n"
						+ "      </nav>\r\n"
						+ "      <div class=\"bg\">\r\n"
						+ "            <table>\r\n"
						+ "                <tr>\r\n"
						+ "                    <th>#Id</th>\r\n"
						+ "                    <th>Type</th>\r\n"
						+ "                    <th>Location</th>\r\n"
						+ "                    <th>Charge</th>\r\n"
						+ "                    <th>Checkout</th>\r\n"
						+ "                </tr>\r\n"
						+ "                <tr>\r\n"
						+                  s1
						+ "                </tr>    \r\n"
						+ "            </table>\r\n"
						+ "      </div>\r\n"
						+ "</body>\r\n"
						+ "</html>";

				out.println(s);
			}else if(checkIn != null) {
				String s1 = "";
				for(int i=0; i<availableRooms.size(); i++) {
					s1+="<tr><th scope='row'>";
					s1+=availableRooms.get(i).roomID + "</th><td>";
					s1+=availableRooms.get(i).roomType + "</td><td>";
					s1+=availableRooms.get(i).roomLocation + "</td><td>";
					s1+=availableRooms.get(i).roomCharge + "</td>";
					s1+="<td><a href=\"home?checkin=1&checkinid=" + availableRooms.get(i).roomID + "\" class=\"btn btn-warning\">CheckIn</a></td>";
				}
// on logout href to login page
// on pressing check out button href to Home?checkout=1
				String s = "<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Hostel Management</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"css/student.css\">\r\n"
						+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x\" crossorigin=\"anonymous\">\r\n"
						+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n"
						+ "    <link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300&display=swap\" rel=\"stylesheet\">\r\n"
						+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" integrity=\"sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n"
						+ "        <div class=\"container-fluid\">\r\n"
						+ "          <a class=\"navbar-brand\" href=\"home?checkout=1\">CheckOut</a>\r\n"
						+ "          <div class=\"collapse navbar-collapse\">\r\n"
						+ "            <ul class=\"navbar-nav\">\r\n"
						+ "              <li class=\"nav-item\">\r\n"
						+ "                <a class=\"nav-link\" href=\"index.html\">Log Out</a>\r\n"
						+ "              </li>\r\n"
						+ "            </ul>\r\n"
						+ "          </div>\r\n"
						+ "        </div>\r\n"
						+ "      </nav>\r\n"
						+ "      <div class=\"bg\">\r\n"
						+ "            <table>\r\n"
						+ "                <tr>\r\n"
						+ "                    <th>#Id</th>\r\n"
						+ "                    <th>Type</th>\r\n"
						+ "                    <th>Location</th>\r\n"
						+ "                    <th>Charge</th>\r\n"
						+ "                    <th>Checkout</th>\r\n"
						+ "                </tr>\r\n"
						+ "                <tr>\r\n"
						+                  s1
						+ "                </tr>    \r\n"
						+ "            </table>\r\n"
						+ "      </div>\r\n"
						+ "</body>\r\n"
						+ "</html>";
				out.println(s);
			}
		}
	}
}
