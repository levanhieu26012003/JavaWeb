<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ SẢN PHẨM</h1>

<c:url value="/motels" var="action" />
<form:form method="post" action="${action}" modelAttribute="motel" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <label for="title">Nhan đề</label>
        <form:input class="form-control" id="title" placeholder='Nhan đề' path="title" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="title">Diện tích</label>
        <form:input class="form-control"  path="area" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="title">Giá</label>
        <form:input class="form-control"  path="price" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="title">Số người ở</label>
        <form:input class="form-control"  path="numberTenant" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="title">Số nhà/Đường</label>
        <form:input class="form-control"  path="address" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="title">Phường/Xã/Thị trấn</label>
        <form:input class="form-control"  path="wards" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="title">Quận/Huyện</label>
        <form:input class="form-control"  path="district" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="title">Tỉnh/Thành phố</label>
        <form:input class="form-control"  path="province" />
    </div>

    <div class="form-floating mb-3 mt-3 image-container">
        <form:input type="file" class="form-control"  id="image" path="files" mutiple="mutiple" />
        <label for="image">Ảnh sản phẩm</label>

        <c:if test="${motel.id > 0}">
            <c:forEach items="${motel.imageCollection}" var="img">
                <div class="image-wrapper"
                        <img  src="${img.url}" width="200" class="img-fluid" />
                </div>
            </c:forEach>
        </c:if>
    </div>
    <div class="form-floating">
        <form:select class="form-select"  path="userId">
            <c:forEach items="${users}" var="c">
                <c:choose>
                    <c:when test="${c.id==motel.userId.id}">
                        <option value="${c.id}" selected>${c.username}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.username}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="categoryId" class="form-label">Danh mục:</label>
    </div>
    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${motel.id > 0}"> Cập nhât sản phẩm</c:when>
                <c:otherwise> Thêm sản phẩm</c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id" />

    </form:form>


    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const closeButtons = document.querySelectorAll(".close");

            closeButtons.forEach(button => {
                button.addEventListener("click", function (event) {
                    // Ngăn chặn sự kiện click lan truyền đến các phần tử cha
                    event.stopPropagation();
                    // Xóa phần tử cha của nút close (tức là .image-wrapper)
                    this.parentElement.remove();
                });
            });
        });

        function deleteImage(button) {
            var container = button.parentElement;
            container.remove(); // Xóa phần tử chứa hình ảnh và nút xóa
        }
    </script>