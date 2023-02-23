document.querySelector("form").addEventListener("submit", userSignup);

function userSignup(e) {
  e.preventDefault();
  console.log("working");

  let name = document.getElementById("name").value;

  let email = document.getElementById("email").value;

  let password = document.getElementById("password").value;

  let phone = document.getElementById("phone").value;

//   next obj for address

  let country = document.getElementById("country").value;

  let city = document.getElementById("city").value;

  let streetNo = document.getElementById("streetNo").value;

  let buildingName = document.getElementById("buildingName").value;

  let pincode = document.getElementById("pincode").value;

  const customer = {
    customerName: name,
    phone:phone,
    email:email,
    password:password,
    address: {
      country: country,
      city: city,
      streetNo:streetNo,
      buildingName:buildingName,
      pincode: pincode,
    },
  };

  userSignUpFun(customer);
}

// now I am sending data

let userSignUpFun = async (obj) => {

  try {
    let res = await fetch("http://shopeasy-env.eba-xkxpqfpn.ap-south-1.elasticbeanstalk.com//customerController/customer", {
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
      alert("customer created successfuly");
      
    } else {
      let data = await res.json();
      let error = JSON.stringify(data);

      let msg = JSON.parse(error);

      console.log(msg);
      alert(msg.message);
    }

  } catch (error) {
    console.log(error);
    alert("customer created successfuly");
    window.location.href = "../customer login/login.html";
  }
};
