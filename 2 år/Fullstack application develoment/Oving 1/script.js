
    /* count button logic */
    var count = 0;
    var button = document.getElementById("count-button");
    var displayedNum = document.getElementById("displayed-num");
    button.addEventListener("click", function(){
        count += 1;
        displayedNum.innerHTML = count;
    });

    /* hide displayed number logic */ 
    var hideNum = document.getElementById("hidenum-btn");
    var visible = true;
    hideNum.addEventListener("click", ()=>{
        if(visible){
            displayedNum.innerHTML="";
            hideNum.innerHTML="Unhide"
            visible = false;
            return;
        }
        displayedNum.innerHTML = count;
        hideNum.innerHTML="Hide"
        visible = true;
    })


/*Opprett et array med tilfeldige stikkord, og gjengi innholdet som en liste på nettsiden
(bruk ul & li tags) - kan bestemme selv om dette gjøres via knapp eller ved kjøring av
nettsiden.*/

var list = ["green", "eco", "sustainable", "recyclable", "fresh", "clean", "renewable", "organic", "natural", "economical", "profitable",
 "neutral", "safe", "blue", "transformative", "reliable", "0 emission", "life"]

var divs = document.getElementsByClassName("body");
var increment = 0;
for (var i = 0; i < divs.length; i++) {
    let r = Math.floor(Math.random(list.length)*10)
    let c = Math.floor(Math.random(list.length)*10)
    let d = Math.floor(Math.random(list.length)*10)
    const htmlString = `
    <ul class="list">
      <li><a>${list[r]}</a></li>
      <li><a>${list[c]}</a></li>
      <li><a>${list[d]}</a></li>
    </ul>`
   
   
    /*const htmlString = `
    <ul class="list">
      <li><a>${list[i+increment]}</a></li>
      <li><a>${list[i+increment+1]}</a></li>
      <li><a>${list[i+increment+2]}</a></li>
    </ul>
  `;*/
  
  divs[i].innerHTML+= " "+htmlString+" ";
  increment+=2;
}


