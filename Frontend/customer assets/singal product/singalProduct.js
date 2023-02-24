let minus = document.getElementById('minus')
let plus = document.getElementById('plus')
let value = document.getElementById('value')
let cartLogo = document.querySelector('.cartLogo')
let cartModal = document.querySelector('.cartModal')

let modalData = document.getElementById('modalData')
let modalDataclass = document.querySelector('.modalData')

let cartLogoInfo = document.getElementById('cartLogo-info')



let ashish = document.getElementById('bigImg')
let thumbnail = document.querySelectorAll('.thumbnail')
for (let i = 0; i < thumbnail.length; i++) {
    const e = thumbnail[i];
    e.addEventListener('click', ()=> {
        let removeThum = e.src.replace('-thumbnail', "")
        ashish.src = removeThum
    })
}





// -------------------------------

const track = document.querySelector('.carousel__track')
const slides = Array.from(track.children)
const nextButton = document.querySelector('.right')
const prevButton = document.querySelector('.left')
const dotsNav = document.querySelector('.carousel__nav')
const dots = Array.from(dotsNav.children)




// toggle the carousel--------------------
let bigImg = document.getElementById('big-img')
let closeCarousal = document.getElementById('close')
let carousalGrandFather = document.querySelector('.carousal-grandFather')

bigImg.addEventListener('click', ()=> {
    carousalGrandFather.classList.remove('hideGrandFather')
    carousalGrandFather.classList.add('ShowGrandFather')
})

closeCarousal.addEventListener('click', function () {
    carousalGrandFather.classList.add('hideGrandFather')
    carousalGrandFather.classList.remove('showGrandFather')
})





cartinfo()
emptyItems()
// function for change the value of product
let goodsValue = 0;
minus.onclick = function () {
    if (goodsValue<=0) {
        emptyItems()
    } else {
        goodsValue--
        value.innerText = goodsValue;
        cardItems()
        cartinfo()
    }
}
plus.onclick = function () {
    goodsValue++
    value.innerText = goodsValue;
    cardItems()
    cartinfo()
}
function cartinfo() {
    cartLogoInfo.innerText = value.innerText
}


cartLogo.addEventListener('click', ()=> {
    cartModal.classList.toggle('cartHidden')
})

// function for display data
function emptyItems() {
    modalData.innerHTML = `<p id="emptyCartData">Your cart is empty</p>`;
    modalDataclass.classList.add('emptyCartData')
}


function cardItems() {
let intvalue = parseInt(goodsValue)
let totleAmount = intvalue*125;
    modalData.innerHTML = `<div id="modalCon">
    <img id="modalImg" src="images/image-product-1-thumbnail.jpg" alt="image-product-1-thumbnail">
    <div>
      <p>Fall Limited Edition Sneakers</p>
      <div>$125.00 x ${goodsValue} <span id="bold">$ ${totleAmount}.00</span></div>
    </div>
    <img id="delete" src="images/icon-delete.svg" alt="icon-delete">
  </div>
  <button id="checkout-btn">Checkout</button>`
  modalDataclass.classList.add('checkoutModal')
  dltbtn();
}
function dltbtn() {
    let deletebtn = document.getElementById('delete')
    deletebtn.addEventListener('click', ()=> {
        emptyItems();
        goodsValue = 0;
        value.innerText = 0
        cartinfo();
    })
}






// JavaScript for carousal



// this is not working
// const slideWidth = slides[0].getBoundingClientRect().width;
// console.log(slideWidth);


// Arrange the slides next to one another

// slides[0].style.left = slideWidth*0 + 'px'
// slides[1].style.left = slideWidth*1 + 'px'
// slides[2].style.left = slideWidth*2 + 'px'
const setSlidePosition = (slide, index)=> {
    slide.style.left = 610*index +'px'
}
slides.forEach(setSlidePosition)


const moveToSlide = (track, currentSlide, targetSlide) =>{
    track.style.transform = 'translateX(-' + targetSlide.style.left + ')'
    currentSlide.classList.remove('current-slide')
    targetSlide.classList.add('current-slide')
}

const updateDots = (currentDot, targetDot)=> {
    currentDot.classList.remove('current-slide')
    targetDot.classList.add('current-slide')

}


const showHideArrow = (slides, prevButton, nextButton, targetIndex)=> {
    if (targetIndex === 0) {
        prevButton.classList.add('isHidden')
        nextButton.classList.remove('isHidden')
    } else if (targetIndex === slides.length - 1) {
        prevButton.classList.remove('isHidden')
        nextButton.classList.add('isHidden')
    } else {
        prevButton.classList.remove('isHidden')
        nextButton.classList.remove('isHidden')
    }
}


// when I click left, move slides to the left
prevButton.addEventListener('click', e => {
    const currentSlide = track.querySelector('.current-slide')
    const prevSlide = currentSlide.previousElementSibling;

    const prevIndex = slides.findIndex(slide => slide === prevSlide)

    const currentDot = dotsNav.querySelector('.current-slide');
    const prevDots = currentDot.previousElementSibling;
    // move to the next slide
    moveToSlide(track, currentSlide, prevSlide)
    updateDots(currentDot, prevDots)
    showHideArrow(slides, prevButton, nextButton, prevIndex)
})

// when I click right, move slides to the right
nextButton.addEventListener('click', e => {
    const currentSlide = track.querySelector('.current-slide')
    const nextSlide = currentSlide.nextElementSibling;
    const currentDot = dotsNav.querySelector('.current-slide');

    const nextIndex = slides.findIndex(slide => slide === nextSlide)

    const nextDots = currentDot.nextElementSibling;
    // move to the next slide
    moveToSlide(track, currentSlide, nextSlide)
    updateDots(currentDot, nextDots)
    showHideArrow(slides, prevButton, nextButton, nextIndex)
})

// when I click the nav indicators, move to that slide
dotsNav.addEventListener('click', e => {
    // What indicator was clicked on?
    const targetDot = e.target.closest('img')
    if (!targetDot) return;
    const currentSlide = track.querySelector('.current-slide');
    const currentDot = dotsNav.querySelector('.current-slide');
    const targetIndex = dots.findIndex(dot => dot === targetDot)
    const targetSlide = slides[targetIndex]

    moveToSlide(track, currentSlide, targetSlide)
    updateDots(currentDot, targetDot)
    showHideArrow(slides, prevButton, nextButton, targetIndex)
})




let menu = document.getElementById('menu')

let burger = document.querySelector('burger-container')

let close11 = document.getElementById('close-1')

menu.addEventListener('click', ()=> {
    burger.classList.remove('hamIsHide')
    burger.classList.add('hamshow')

})
close11.addEventListener('click', ()=> {
    burger.classList.add('hamIsHide')
})




