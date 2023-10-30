var allBtns=document.getElementsByTagName("button");
var ansBtns = document.getElementsByClassName("answer-btn");
var answerLine = document.getElementById("answer-line");
var answerDiv = document.getElementById("choises-div");


function insertButtonInAnswer(element) {

    console.log("click");
    answerLine.appendChild(element);

}

function returnButtonFromAnswer(element){

    console.log("click2");
    answerDiv.appendChild(element);

}

function handleAnsBtnClick(element){

    console.log(element.parentNode)
    if(element.parentNode==answerDiv){
        console.log("jeProslo");
        insertButtonInAnswer(element);
    }
    else if(element.parentNode==answerLine){
        console.log("jeProslo");
        returnButtonFromAnswer(element);
    }
    console.log("ASSDF");


}

function doButtonAnimation(element) {

    

}


for (let index = 0; index < allBtns.length; index++) {
    const element = allBtns[index];
    element.addEventListener("click", function () {
        doButtonAnimation(element);
    });
}
    


for (let index = 0; index < ansBtns.length; index++) {
    
    const idElement="btn" + index;
    const element = ansBtns[index];
    element.id=idElement;

    console.log(element);
    element.addEventListener("click", function () {
        handleAnsBtnClick(element);
    });
}