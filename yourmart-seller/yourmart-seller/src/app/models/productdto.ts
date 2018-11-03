import {product} from './product'
export interface productdto{
    status:number;
    data:Array<product>;
    message:string
}