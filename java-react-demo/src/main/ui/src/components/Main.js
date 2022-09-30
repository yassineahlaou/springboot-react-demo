import React from 'react'

import { useState, useEffect } from "react";
import axios from 'axios';

import './main.scss';

export default function Main() {
	
	const [list, setList] = useState([]);
	const [inputs, setInputs] = useState({});
	
	
	const [email,setEmail] = useState("");
	const [firstName,setFirstName] = useState("");
	const [lastName,setLastName] = useState("");
	const [age,setAge] = useState();
	
	const [errorReg, setErrorReg] = useState("")
	const [updateClicked, setUpdateClicked] = useState("");
	
	
	
		const getUsers = async ()=>{
			
			
			const users = await axios.get('http://localhost:8080/api/users');
			//console.log(users.data)
			setList(users.data);
			
			 /*console.log(email);
			 console.log(firstName);
			 console.log(lastName);
			 console.log(age);*/
			
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
      
        const showUpdate = async (e)=>{
       let  id = e.currentTarget.getAttribute('userid');
      
       try{
       let foundUser = await axios.get(`http://localhost:8080/getUser/${id}`)
       
       setUpdateClicked(foundUser.data)
       setEmail(foundUser.data.email);
       setFirstName(foundUser.data.firstName);
       setLastName(foundUser.data.lastName);
       setAge(foundUser.data.age);
       }catch(error){
	console.log(error.response.data);
		}
	
	
    }
    
     const closeUpdate = async (e)=>{
       
       try{
       
       
       setUpdateClicked({})
       }catch(error){
		console.log(error.response.data);
		}

	 
    }
    
    const saveUpdate = async (e)=>{
	 let  id = e.currentTarget.getAttribute('userid');
	 
       try{
      await axios.put(`http://localhost:8080/storeUpdate/${id}` ,  {email,firstName,lastName,age} )
       
       
       }catch(error){
	console.log(error.response.data);
}

setUpdateClicked({})

		
       
	
		
	
}

const deleteProcess = async (e)=>{
	let  id = e.currentTarget.getAttribute('userid');
	await axios.delete(`http://localhost:8080/deleteUser/${id}`)
	
}
      
     
	return (
    	
    
    <div className='main'>
    	<div> {list.length}</div>
    	<div className="list">
    	{list?.map((item)=>
			<div className="item">	
			
				{updateClicked.id == item.id ? (<input type="email" placeholder='email' name="email" defaultValue={item.email} onChange={(e)=>setEmail(e.target.value)} required></input>) : (<p>{item.email}</p>)}
				{updateClicked.id == item.id ? (<input type="text" placeholder='firstname' name="firstName" defaultValue={item.firstName} onChange={(e)=>setFirstName(e.target.value)} required></input>) : (<p>{item.firstName}</p>)}
				{updateClicked.id == item.id ? (<input type="text" placeholder='lastname' name="lastName" defaultValue={item.lastName} onChange={(e)=>setLastName(e.target.value)} required></input>) : (<p>{item.lastName}</p>)}
				{updateClicked.id == item.id ? (<input type="number" placeholder='age' name="age" defaultValue={item.age} onChange={(e)=>setAge(e.target.value)} required></input>) : (<p>{item.age}</p>)}
				{ updateClicked.id == item.id ? <button type="submit" userid={item.id} onClick={(e)=>saveUpdate(e)}>Save</button> : <button userid={item.id} onClick={(e)=>showUpdate(e)}>Update</button> }
				{ updateClicked.id == item.id ? <button  userid={item.id} onClick={(e)=>closeUpdate(e)}>Cancel</button> : '' }
				<button userid={item.id} onClick={(e)=>deleteProcess(e)} type="submit" >Delete</button></div>
				
				
)}
</div>

		<div className="form">
			<input type="email" placeholder='email' name="email" value={inputs.email} onChange={handleChange} required></input>
			<input type="text" placeholder='firstname' name="firstName" value={inputs.firstName} onChange={handleChange} required ></input>
			<input type="text" placeholder='lastname' name="lastName"  value={inputs.lastName} onChange={handleChange} required></input>
			<input type="number" placeholder='age' name="age" value={inputs.age} onChange={handleChange} required ></input>
			<button type="submit" onClick={handleUpload}>Upload</button>
			
		</div>
		<div>
			 <span >{errorReg}</span>
		</div>
    </div>
    )
	}