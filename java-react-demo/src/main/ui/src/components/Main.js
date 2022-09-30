import React from 'react'

import { useState, useEffect } from "react";
import axios from 'axios';


export default function Main() {
	
	const [list, setList] = useState([]);
	const [inputs, setInputs] = useState({});
	const [errorReg, setErrorReg] = useState("")
	
	
		const getUsers = async ()=>{
			
			
			const users = await axios.get('http://localhost:8080/api/users');
			//console.log(users.data)
			setList(users.data);
			
		}
		
		useEffect(()=>{
		    
		 getUsers()
		 //console.log(list);
		    
		},[list])
		
		const handleChange = (e) =>{
        setInputs((prev) => {
            return { ...prev, [e.target.name]: e.target.value };
          }); 

    }
     const handleUpload = async (e) =>{ 
	 e.preventDefault();
       try{
        await axios.post("http://localhost:8080/addUser", {...inputs})
         setInputs({firstName:"" , email:"" , lastName:"", age:""})
         setErrorReg("")
        }
        catch(error){
			console.log(error);
			 !error.response.data.includes("Proxy") ? setErrorReg(error.response.data) : setErrorReg("Check Your Connection")
		}
       
       
        

      }
      
     
	return (
    	
    
    <div className='main'>
    	<div> {list.length}</div>
    	
    	{list?.map((item)=>
				<p>{item.email} + {item.firstName} + {item.lastName} + {item.paymentDate}</p>
				
				
)}

		<div>
			<input type="email" placeholder='email' name="email" value={inputs.email} onChange={handleChange} ></input>
			<input type="text" placeholder='firstname' name="firstName" value={inputs.firstName} onChange={handleChange} ></input>
			<input type="text" placeholder='lastname' name="lastName"  value={inputs.lastName} onChange={handleChange} ></input>
			<input type="number" placeholder='age' name="age" value={inputs.age} onChange={handleChange} ></input>
			<button type="submit" onClick={handleUpload}>Upload</button>
		</div>
		<div>
			 <span >{errorReg}</span>
		</div>
    </div>
    )
	}