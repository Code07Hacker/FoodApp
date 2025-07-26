import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { toast } from 'react-toastify';

const ListFood = () => {
  const [list , setList] = useState([]);
  const fetchList = async()=>{
    const response = await axios.get('http://localhost:8080/api/foods')
    console.log(response.data)
    if(response.status === 200){
      setList(response.data)
    }
    else{
      toast.error("Error while reading the Foods")
    }
  }
  useEffect(()=>{
    fetchList();
  },[])
  return (
    <div>ListFood</div>
  )
}

export default ListFood