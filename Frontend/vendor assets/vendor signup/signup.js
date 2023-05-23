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
      imageUrl: profileImage,
      state: state,
      city: city,
      pincode: pincode,
    },
  };

  userSignUpFun(vendor);
}

// now I am sending data

let userSignUpFun = async (obj) => {

  try {
    let res = await fetch("http://localhost:8888/vendorController/vendor", {
      method: "POST",
      body: JSON.stringify(obj),
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (res.ok) {
      console.log("sucesss");
      let data = await res.json();

      // To get data from response   // user data
      // let userData=JSON.stringify(data)
      let d = JSON.stringify(data);

      console.log(d);
      alert("Vendor created successfuly");
      
    } else {
      let data = await res.json();
      let error = JSON.stringify(data);

      let msg = JSON.parse(error);

      console.log(msg);
      alert(msg.message);
    }

  } catch (error) {
    console.log(error);
    alert("Vendor created successfuly");
    window.location.href = "../vendor login/login.html";
  }
};
