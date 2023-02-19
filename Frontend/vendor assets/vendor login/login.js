document.querySelector("form").addEventListener("submit", userSignup);

function userSignup(e) {
  e.preventDefault();
  console.log("working");

  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;


  const loginData = {

      email: email,
      password: password,
      user_type:"VENDOR"
  };

  userSignUpFun(loginData);

}



// now I am sending data

let userSignUpFun = async (obj) =>{
    

    let res = await fetch("http://localhost:8888/loginController/login", {
        method: "POST",
        body: JSON.stringify(obj),
        headers: {
          "Content-Type": "application/json",
        },
      })
    
      // let data = await res.json();
      // return data;
      // console.log(res.json());
      alert("Welcome Vendor, Login successfuly")
    //   window.location.href = "../vendor login/login.html";
      return res;
     
  }
  

