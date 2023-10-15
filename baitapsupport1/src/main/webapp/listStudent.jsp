<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form action="students">

    <c:forEach var="list" items="${lists}">
        <div>
            <div>
                <div>
                    <input type="hidden" name="id" value="${list.id}"/>
                    <input type="hidden" name="name" value="${list.name}"/>
                    <input type="hidden" name="age" value="${list.age}"/>
                    <input type="hidden" name="address" value="${list.address}"/>
                </div>

                <table align="center" border="1" border-color="black" border-style="solid" border-collapse="collapse">
                    <tr>
                        <th>Họ và Tên</th>
                        <th>Tuổi</th>
                        <th>Địa Chỉ</th>
                        <th>Sửa thông tin</th>
                    </tr>
                    <tr>
                        <td> ${list.name}</td>
                        <td>${list.age}</td>
                        <td>${list.address}</td>
                        <td><button name="action" value="delete">Xóa</button></td>
                    </tr>


                </table>
            </div>
        </div>
    </c:forEach>
    <button type="submit" name="action" value="add">Thêm</button>

</form>
</body>
</html>
