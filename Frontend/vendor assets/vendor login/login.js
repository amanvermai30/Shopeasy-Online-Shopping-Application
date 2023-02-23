const sessionKey = localStorage.getItem("sessionKey");
console.log(sessionKey);

document.querySelector("form").addEventListener("submit", userSignup);

async function userSignup(e) {
  e.preventDefault();
  console.log("working");

  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;

  const loginData = {
    email: email,
    password: password,
    user_type: "VENDOR",
  };

  try {
    const sessionKey = await userSignUpFun(loginData);
    localStorage.setItem('sessionKey', sessionKey);
    console.log(sessionKey);
    alert("Vendor login successfully");
    window.location.href = "../dashboard/dashboard.html";
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
      return data.sessionKey;

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


if(sessionKey !=null ){
  window.location.href = "../dashboard/dashboard.html";
}