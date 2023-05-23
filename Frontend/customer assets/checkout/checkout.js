
const orderId = localStorage.getItem("order_id");
const totalAmount = localStorage.getItem("totalAmount");

const totalPriceInput = document.getElementById("totalprice");
totalPriceInput.value = totalAmount;

function paymentNowFun() {


    fetch(`http://localhost:8888/paymentController/giveyourpayment/${orderId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        method:"CREDIT_CARDS"
      })
    })
    .then(response => response.json())
    .then(data => {
      // Handle the response data
      console.log(data);
      window.location.href = "../thank you page/thankyou.html";
  
    })
    .catch(error => {
      // Handle errors
      console.error(error);
    });
  
  }