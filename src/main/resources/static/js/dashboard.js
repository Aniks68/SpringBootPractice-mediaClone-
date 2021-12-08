function like(postId, userId){
    console.log("working", postId, userId);
    const URL = "/processLike";
    let like = document.getElementById(postId).style.color;
    console.log(like);
    if(like == "rgb(25, 119, 242)"){
        console.log("decrement");
        const valid = document.getElementsByClassName("thumb");
        document.getElementById(postId).style.color = "#ffffff";
        for (let i = 0; i < valid.length; i++) {
            let newId = valid[i].innerHTML.split(" ")[0];
            if(newId == postId){
                let like = Number(document.getElementsByClassName("likes")[i].innerHTML);
                like--;
                document.getElementsByClassName("likes")[i].innerHTML = like+"";
                console.log( document.getElementsByClassName("likes")[i]);
                const data = {postId, userId, "action": 0}
                ajaxCall(URL, data);
            }
        }
    }else{
        console.log("increment");
        const valid = document.getElementsByClassName("thumb");
        document.getElementById(postId).style.color = "#1977f2";
        for (let i = 0; i < valid.length; i++) {
            let newId = valid[i].innerHTML.split(" ")[0];
            if(newId == postId){
                let like = Number(document.getElementsByClassName("likes")[i].innerHTML);
                like++;
                document.getElementsByClassName("likes")[i].innerHTML = like+"";
                const data = {postId, userId, "action": 1}
                ajaxCall(URL, data);
            }
        }
    }
}