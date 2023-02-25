document.querySelector("form").addEventListener("submit", userLogin);

async function userLogin(e) {
  e.preventDefault();
  console.log("working");

  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;

  const loginData = {
    email: email,
    password: password,
    user_type: "CUSTOMER",
  };

  try {
    const data = await userSignUpFun(loginData);
    localStorage.setItem("sessionkey",data.sessionkey);
    localStorage.setItem("userId",data.userId);
    console.log(data.sessionkey);
    alert("Start your shopping now");
    window.location.href = "/index.html";

  } catch (error) {
    console.error(error);
    alert("Error: " + error.message);
  }

}

let userSignUpFun = async (obj) => {

  try {
    const res = await fetch("http://shopeasy-env.eba-xkxpqfpn.ap-south-1.elasticbeanstalk.com//loginController/login", {
      method: "POST",
      body: JSON.stringify(obj),
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (res.ok) {
      console.log("success");
      const data = await res.json();
      return data;

    } else {

      const data = await res.json();
      const error = JSON.stringify(data);
      const msg = JSON.parse(error);
      console.log(msg);
      throw new Error(msg.message);

    }
  } catch (error) {
    console.log(error);
    throw new Error(error);
  }

};
