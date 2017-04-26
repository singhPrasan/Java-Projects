var fgImg = null;
var bgImg = null;

function loadForegroundImage(){
    var file = document.getElementById('f1');
    fgImg = new SimpleImage(file);
    var canvas = document.getElementById('can1');
    fgImg.drawTo(canvas);
}

function loadBackgroundImage(){
    var file = document.getElementById('f2');
    bgImg = new SimpleImage(file);
    var canvas = document.getElementById('can2');
    bgImg.drawTo(can2);
}

function applyBackground(){
    if(fgImg == null || !fgImg.complete()){
        alert("Foreground Image not loaded");
    }
    if(bgImg == null || !bgImg.complete()){
        alert("Background Image not loaded");
    }
    clearCanvas();
    var output = new SimpleImage(fgImg.getWidth(), fgImg.getHeight());

    for(pixel of fgImg.values()){
        var X = pixel.getX();
        var Y = pixel.getY();
        var bgPixel = bgImg.getPixel(X,Y);
        if(pixel.getGreen() > pixel.getRed() + pixel.getBlue()){
            output.setPixel(X,Y,bgPixel);
        }else{
            output.setPixel(X,Y, pixel);
        }
    }

    var can1 = document.getElementById('can1');
    output.drawTo(can1);
}

function clearCanvas(){
    var can1 = document.getElementById('can1');
    var ctx1 = can1.getContext('2d');
    ctx1.clearRect(0,0,can1.width, can1.height);

    var can2 = document.getElementById('can2');
    var ctx2 = can2.getContext('2d');
    ctx2.clearRect(0,0,can2.width, can2.height);
}