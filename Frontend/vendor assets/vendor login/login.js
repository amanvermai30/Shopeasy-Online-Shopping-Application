document.querySelector("form").addEventListener("submit", userSignup);

function userSignup(e) {
  e.preventDefault();
  console.log("working");

  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;

  const loginData = {
    email: email,
    password: password,
    user_type: "VENDOR",
  };

  userSignUpFun(loginData);
}

// now I am sending data

let userSignUpFun = async (obj) => {

  try {
    let res = await fetch("http://localhost:8888/loginController/login", {
      method: "POST",
      body: JSON.stringify(obj),
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (res.ok) {
      console.log("sucesss");

      let data = await res.json();

      const sessionKey = res.headers.get('Session-Key');
      console.log(res.headers);
      console.log(sessionKey);
      // To get data from response   // user data
      // let userData=JSON.stringify(data)
      localStorage.setItem('loginToken', sessionKey);
      let d = JSON.stringify(data);

      console.log(d);
      alert("Vendor Login successfuly");
    } else {
      let data = await res.json();
      let error = JSON.stringify(data);

      let msg = JSON.parse(error);

      console.log(msg);
      alert(msg.message);
    }
  } catch (error) {
    console.log(error);
    alert("Vendor Login successfuly");
    // window.location.href = "../vendor login/login.html";
  }

};