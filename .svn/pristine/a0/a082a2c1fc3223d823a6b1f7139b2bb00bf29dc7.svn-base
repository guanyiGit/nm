



function MySelct(options) {
    debugger
    $.ajax({
        url:options.url,
        type:"get",
        data:options.params,
        success:(list)=>{
            let buffer= new StringBuffer();
            var  value = options.value;
            var  text  = options.text;
            if(!options.hasOwnProperty('sv')){
                options.sv='';
            }

            for(index in list){
                var v = list[index][value] ;

                if(v==options.sv){
                    buffer.append('<option selected = "selected" value="'+v+'">');
                }else {
                    buffer.append('<option value="'+v+'">');
                }
                buffer.append(list[index][text]);
                buffer.append('</option>');
            }
            $("#"+options.id).append(buffer.toString());
        }
    });
}