<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>EmailCodeDemo</title>
</head>

<body>
<form action="register" method="post">
    <table align="center">
        <tr>
            <td colspan="2"><h2>Welcome to EmailCode demo</h2></td>
        </tr>
        <tr>
            <td><%
                String message =(String) request.getAttribute("message");
                if (message!=null){
                    out.print("<font color='red'>"+message+"</font>");
                }
            %></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input id="email" type="email" name="useremail" value="<%
            String email = (String) request.getAttribute("email");
            if (email != null) {
                out.print(email);
            }
            %>"></td>
        </tr>
        <tr>
            <td>Code:</td>
            <td><input type="text" name="emailcode"></td>
            <td><input id="getCode" type="button" value="get code"></td>
        </tr>
        <tr>
            <td><input id="submit" type="submit" value="submit"></td>
        </tr>

    </table>
</form>
</body>
<script>
    let getCodeBtn = document.querySelector("#getCode");
    //

    getCodeBtn.addEventListener("click", () => {
        let email1 = document.querySelector("#email").value;
        console.log(email1)
        const formData = new FormData();
        formData.append("email", email1);
        fetch("mail", {
            body: 'email='+email1,
            headers: {
                "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
            },
            method: "post",
        }).then(response => response.text())
        .then(status => {
            console.log(typeof status)
            console.log(status)
            const code = parseInt(status);
            console.log(code === 0)
            if (code === 1) {
                // document.querySelector("#submit").disabled = false;
            } else if (code == 2) {
                alert("You had registered!");
            }
        })
    })
</script>
</html>