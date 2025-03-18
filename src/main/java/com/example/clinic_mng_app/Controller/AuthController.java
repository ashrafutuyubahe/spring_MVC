package com.example.clinic_mng_app.Controller;


    import com.example.clinic_mng_app.Model.ClinicManager;
    import com.example.clinic_mng_app.ServiceImpl.AuthServiceImpl;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.*;

    import java.io.IOException;
    import java.sql.SQLException;
    @WebServlet("/auth")
    public class AuthController extends HttpServlet {

        private final AuthServiceImpl authService;

        public AuthController() throws SQLException {
            this.authService = new AuthServiceImpl();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");

            if ("login".equals(action)) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                ClinicManager user = authService.authenticateUser(email, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInUser", user);
                    System.out.println("User logged in successfully.");
                    response.sendRedirect("dashboard");
                } else {
                    response.sendRedirect("login? =Invalid credentials");
                }
            } else if ("register".equals(action)) {
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                ClinicManager newUser = new ClinicManager();
                newUser.setClinicManagerEmail(email);
                newUser.setClinicManagerName(username);
                newUser.setClinicManagerPassword(password);

                if (authService.registerUser(newUser)) {
                    response.sendRedirect("login");
                    System.out.println("User registered successfully.");
                } else {
                    response.sendRedirect("register?error=Registration failed");
                }
            } else if ("logout".equals(action)) {
                authService.logout(request);
                System.out.println("User logged out successfully.");


                response.sendRedirect("login");
            }
        }
    }
