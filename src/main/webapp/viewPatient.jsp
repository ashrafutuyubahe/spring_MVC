<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.example.clinic_mng_app.Model.Patient" %>
<%@ page import="com.example.clinic_mng_app.Model.Patient" %>

<%
  List<Patient> patients = (List<Patient>) request.getAttribute("patients");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Manage Patients</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="text-center mb-3">
    <a href="http://localhost:8080/Clinic_mng_app_war_exploded/dashboard" class="btn btn-secondary btn-sm">Go to Dashboard</a>
  </div>

  <h2 class="text-center mb-4">Manage Patients</h2>

  <div class="card p-4">
    <h4 class="mb-3">Patient List</h4>
    <table class="table table-bordered">
      <thead class="table-dark">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>Diagnosis</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody id="patientTableBody">
      <%
        if (patients != null && !patients.isEmpty()) {
          for (Patient patient : patients) {
      %>
      <tr id="row-<%= patient.getId() %>">
        <td><%= patient.getId() %></td>
        <td><%= patient.getName() %></td>
        <td><%= patient.getAge() %></td>
        <td><%= patient.getDiagnosis() %></td>
        <td>
          <button class="btn btn-primary btn-sm" onclick="showEditModal(<%= patient.getId() %>, '<%= patient.getName() %>', <%= patient.getAge() %>, '<%= patient.getDiagnosis() %>')">Edit</button>
          <button onclick="deletePatient(<%= patient.getId() %>)" class="btn btn-danger btn-sm">Delete</button>
        </td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
    <p id="noPatients" class="text-muted" style="display: none;">No patients found.</p>
  </div>
</div>

<div class="modal fade" id="editPatientModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editModalLabel">Edit Patient</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="editPatientForm" method="POST">
          <!-- Form to update the patient -->
          <input type="hidden" id="editPatientId" name="id">
          <div class="mb-3">
            <label for="editPatientName" class="form-label">Name</label>
            <input type="text" class="form-control" id="editPatientName" name="name" required>
          </div>
          <div class="mb-3">
            <label for="editPatientAge" class="form-label">Age</label>
            <input type="number" class="form-control" id="editPatientAge" name="age" required>
          </div>
          <div class="mb-3">
            <label for="editPatientDiagnosis" class="form-label">Diagnosis</label>
            <input type="text" class="form-control" id="editPatientDiagnosis" name="diagnosis" required>
          </div>
          <button type="submit" class="btn btn-primary">Save Changes</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>

  function showEditModal(id, name, age, diagnosis) {
    document.getElementById("editPatientId").value = id;
    document.getElementById("editPatientName").value = name;
    document.getElementById("editPatientAge").value = age;
    document.getElementById("editPatientDiagnosis").value = diagnosis;

    var editModal = new bootstrap.Modal(document.getElementById('editPatientModal'));
    editModal.show();
  }



  document.getElementById("editPatientForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const id = document.getElementById("editPatientId").value;
    const name = document.getElementById("editPatientName").value;
    const age = document.getElementById("editPatientAge").value;
    const diagnosis = document.getElementById("editPatientDiagnosis").value;


    fetch("http://localhost:8080/Clinic_mng_app_war_exploded/ManagePatientsController", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        id: id,
        name: name,
        age: age,
        diagnosis: diagnosis
      })
    })
            .then(response => {
              if (response.ok) {
                alert("Patient updated successfully.");
                fetchPatients();
                var editModal = bootstrap.Modal.getInstance(document.getElementById('editPatientModal'));
                editModal.hide();
              } else {
                return response.text().then(text => { throw new Error(text); });
              }
            })
            .catch(error => {
              console.error("Error:", error);
              alert("Failed to update patient.");
            });
  });




  function deletePatient(patientId) {
    if (confirm("Are you sure you want to delete this patient?")) {
      fetch("http://localhost:8080/Clinic_mng_app_war_exploded/ManagePatientsController?id=" + patientId, {
        method: "DELETE"
      })

              .then(response => {
                if (response.ok) {
                  alert("Patient removed successfully.");
                  fetchPatients();
                } else {
                  return response.text().then(text => { throw new Error(text); });
                }
              })
              .catch(error => {
                console.error("Error:", error);
                alert("Failed to delete patient.");
              });
    }
  }


  function fetchPatients() {
    fetch("http://localhost:8080/Clinic_management_syst_war_exploded/getPatients")
            .then(response => response.json())
            .then(patients => {
              let tableBody = document.getElementById("patientTableBody");
              tableBody.innerHTML = "";
              if (patients.length === 0) {
                document.getElementById("noPatients").style.display = "block";
              } else {
                document.getElementById("noPatients").style.display = "none";
              }
              patients.forEach(patient => {
                tableBody.innerHTML += `
                    <tr id="row-${patient.id}">
                        <td>${patient.id}</td>
                        <td>${patient.name}</td>
                        <td>${patient.age}</td>
                        <td>${patient.diagnosis}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="showEditModal(${patient.id}, '${patient.name}', ${patient.age}, '${patient.diagnosis}')">Edit</button>
                            <button onclick="deletePatient(${patient.id})" class="btn btn-danger btn-sm">Delete</button>
                        </td>
                    </tr>`;
              });
            });
  }

  fetchPatients();
</script>

</body>
</html>
