
const app = Vue.createApp({
    data(){
      return{
         numbers :  document.querySelectorAll(".number"),
         stringy :  "",
         answer : 0,
         displayed :  false,
         exponent : false,
         displayE : document.querySelector(".display"),
         logL : document.getElementById("log"),
         logs : []
      }
    },
    computed: {
        
    },
    methods: {
        number(n) {
            this.mounted()
            //first number goes to astringy
            if (n != "DEL" && n != "pi" && n != "e") {
                this.stringy += n

                
                this.displayE.innerHTML = this.stringy.replace("*", "×").replace("/", "÷").replace(/Math.PI/g, "π").replace(
                    /Math.E/g, "e").replace("×*", "<sub>").replace("**", "<sub>")
            } else if (n == "pi" || n == "e") {
                if(n == "pi"){
                this.stringy += `Math.PI`
            }
            else if(n == "**"){
                if(this.exponent){
                    this.displayE.innerHTML = this.stringy.push()
                }
                this.exponent != this.exponent
            }
            else{
                this.stringy+="Math.E"
            }
            this.mounted()
            this.displayE.innerHTML = this.stringy.replace("*", "×").replace("/", "÷").replace(/Math.PI/g, "π").replace(/Math.E/g, "e").replace("×*", "^")
            } 
            

            if (n == "DEL") {
                    this.stringy = this.stringy.slice(0,-1);
                    this.displayE.innerHTML=`${stringy.slice(0,-1)}`
            }
        },
        calculate() {
            this.mounted()
            answer = eval(this.stringy)
            console.log(this.stringy);
            this.displayE.innerHTML = `${answer}`
            this.logs.push(`${this.stringy} = ${answer}`);
            console.log(this.logs)
            this.displayed = true
        },
        reset() {
            this.mounted()
            this.stringy = "";
            this.displayE.innerHTML = 0;
        },
        mounted(){
            this.displayE = document.querySelector(".display");
            this.logL = document.getElementById("#logs");
          },
          replace(replacable){
            return new String(replacable).replace("*", "×").replace("/", "÷").replace(/Math.PI/g, "π").replace(
                /Math.E/g, "e").replace("×*", "<sub>").replace("**", "<sub>")
          }
      }
  })
  
  
  