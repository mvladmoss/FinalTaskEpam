let tBackgroundElem = document.querySelector(`.login-wrap`);
// массив ссылок на изображения
let images = [
  `http://slimfaq.in.ua/wp-content/uploads/2016/12/f7d9f8db71acb6a85bd21fca6565d903.jpg`,
  `https://images.oyster.com/photos/fitness-center--v11827839-cc-720.jpg`,
  'https://images.oyster.com/photos/fitness-center--v9449424-cc-720.jpg'
]
let i = 0;
function changeBackgroundImage() {
        // если дошли до конца массива начинаем с первой
    if(i === images.length) {
    i = 0;
  }
       // устанавливаем свойство backgroundImage
  tBackgroundElem.style.backgroundImage = `url(${images[i]})`;
  i++;
};
 
let interval = setInterval(changeBackgroundImage, 5000);
