import React, { useEffect, useState } from 'react'
import {assets} from '../../assets/assets'
import axios from 'axios';

const AddFood = () => {
  const [image,setImage] = useState(false);
  const [data , setData] = useState({
    name:'',
    description:'',
    price:'',
    category:'Biryani'
  })

  const onChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setData(data=>({...data,[name]:value}))
  }

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    if(!image){
      alert('Please select an image')
      return;
    }

    const formData = new FormData();
    formData.append('food',JSON.stringify(data));
    formData.append('file',image);

    try {
      const response = await axios.post('http://localhost:8080/api/foods',formData ,{headers:{"Content-Type":"multipart/form-data"}})
      if(response.status===200){
        alert('Food added successfully')
        setData({name:'',description:'',category:'Biryani',price:''})
        setImage(null);
      }
    } catch (error) {
      console.log('Error',error);
      alert('Error adding Food')
    }
  }

  return (
    <div className="mx-2 mt-2">
    <div className="row">
      <div className="card col-md-4">
        <div className="card-body">
          <h2 className="mb-4">Add Food</h2>
          <form onSubmit={onSubmitHandler}>
            <div className="mb-3">
              <label htmlFor="image" className="form-label">
                <img src={image ? URL.createObjectURL(image) : assets.upload} alt="" width={90} height={90}/>
              </label>
              <input type="file" className="form-control" id="image" hidden onChange={(e)=>setImage(e.target.files[0])}/>
            </div>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">Name</label>
              <input type="text" className="form-control" id="name" required name='name' onChange={onChangeHandler} value={data.name}/>
            </div>
            <div className="mb-3">
              <label htmlFor="category" className="form-label">category</label>
              <select name='category' id='category' className='form-control' onChange={onChangeHandler} value={data.category}>
                <option value="Biryani">Biryani</option>
                <option value="Cake">Cake</option>
                <option value="Pizza">Pizza</option>
                <option value="Rolls">Rolls</option>
                <option value="Salad">Salad</option>
                <option value="Ice-cream">Ice-cream</option>
              </select>
            </div>
            <div className="mb-3">
              <label htmlFor="price" className="form-label">Price</label>
              <input type="number" className="form-control" id="price" required name='price' onChange={onChangeHandler} value={data.price}/>
            </div>
            <div className="mb-3">
              <label htmlFor="description" className="form-label">Description</label>
              <textarea className="form-control" id="message" rows="5" required name='description' onChange={onChangeHandler} value={data.description}></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Send Message</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  )
}

export default AddFood