import React from "react";
//prima props od roditel (toa sto go prakam preku this.state...)
const destinacii = (props) => {
    //funkcionalna komponenta, imam samo return
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Country</th>
                        </tr>
                        </thead>
                        <tbody>
                        {/*koristam props za da pristapam do destinacii koi gi predadov vo App kon
                         ovaa dete-komponenta i za sekoj term zemam ime i drzava koi gi prikazuvam*/}
                        {props.destinacii.map((term) => {
                            return (
                                <tr>
                                    <td>{term.name}</td>
                                    <td>{term.country}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default destinacii;