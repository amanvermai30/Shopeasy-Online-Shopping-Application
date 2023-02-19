document.querySelector("form").addEventListener("submit", userSignup);

function userSignup(e) {
  e.preventDefault();
  console.log("working");

  let name = document.getElementById("name").value;

  let email = document.getElementById("email").value;

  let password = document.getElementById("password").value;

  let country = document.getElementById("country").value;

  let state = document.getElementById("state").value;

  let city = document.getElementById("city").value;

  let pincode = document.getElementById("pincode").value;

  let phone = document.getElementById("phone").value;

  let profileImage = document.getElementById("image").value;


  const vendor = {
    venderName: name,
    personalInfo: {
      phone: phone,
      email: email,
      password: password,
      country: country,
      imageUrl:profileImage,
      state: state,
      city: city,
      pincode: pincode,
    },
  };

  userSignUpFun(vendor);

}



// now I am sending data

let userSignUpFun = async (obj) =>{
    

    let res = await fetch("http://localhost:8888/vendorController/vendor", {
        method: "POST",
        body: JSON.stringify(obj),
        headers: {
          "Content-Type": "application/json",
        },
      })
    
      // let data = await res.json();
      // return data;
      // console.log(res.json());
      alert("Vendor created successfuly")
      window.location.href = "../vendor login/login.html";
      return res;
     
  }
  

