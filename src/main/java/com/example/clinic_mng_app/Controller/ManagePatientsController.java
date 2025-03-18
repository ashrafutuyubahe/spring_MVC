package com.example.clinic_mng_app.Controller;

import com.example.clinic_mng_app.Model.Patient;
import com.example.clinic_mng_app.Service.PatientManagerService;
import com.example.clinic_mng_app.ServiceImpl.PatientManagementImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ManagePatientsController")
public class ManagePatientsController extends HttpServlet {

    private final PatientManagerService patientManagerService;

    public ManagePatientsController() throws SQLException {
        this.patientManagerService = new PatientManagementImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String diagnosis = req.getParameter("diagnosis");

        patientManagerService.addPatient(name, age, diagnosis);

        resp.sendRedirect("view");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient> patients = patientManagerService.getAllPatients();
        req.setAttribute("patients", patients);
        req.getRequestDispatcher("/viewPatient.jsp").forward(req, resp);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        System.out.println("about to delete");
        System.out.println(idParam);
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                patientManagerService.removePatient(id);
                resp.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid patient ID");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Patient ID is required");
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            sb.append(line);
        }

        String jsonData = sb.toString();


        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            int age = jsonObject.getInt("age");
            String diagnosis = jsonObject.getString("diagnosis");

            // Call the update method in the service
            patientManagerService.updatePatient(id, name, age, diagnosis);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Patient updated successfully");
        } catch (JSONException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON data");
        }
    }

}
