<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<%@taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Banners</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4"><i class="bi bi-image"></i> Listado de Banners</h2>
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th><i class="bi bi-card-heading"></i> Título</th>
            <th><i class="bi bi-calendar-date"></i> Fecha</th>
            <th><i class="bi bi-file-earmark-image"></i> Archivo</th>
            <th><i class="bi bi-check-circle"></i> Estatus</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="banner" items="${banners}">
            <tr>
                <td>${banner.titulo}</td>
                <td>${banner.fecha}</td>
                <td>${banner.archivo}</td>
                <td>
                    <c:choose>
                        <c:when test="${banner.estatus eq 'Activo'}">
                            <span class="badge bg-success"><i class="bi bi-check-circle"></i> Activo</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-danger"><i class="bi bi-x-circle"></i> Inactivo</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
