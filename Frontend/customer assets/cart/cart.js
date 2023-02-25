// serch product by category
const aTags = document.querySelectorAll('.productCatogary a');

  for (let i = 0; i < aTags.length; i++) {
    
      aTags[i].addEventListener('click', function(event) {
      event.preventDefault(); 
      const clickedTag = event.target.textContent;
      localStorage.setItem('category', clickedTag);

      if(clickedTag == "Mens"){
        window.location.href = "/customer assets/category/mans/mans.html";

      }else if(clickedTag == "Womans"){
        window.location.href = "/customer assets/category/womans/womans.html";

      }else if(clickedTag == "Kids"){
        window.location.href = "/customer assets/category/kids/kids.html";

      }else {
        window.location.href = "/customer assets/category/grocery/grocery.html";
      }

    });
  }