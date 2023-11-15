import { useState } from "react";
import "./Search.scss";

const SearchComponent = () => {
  const [selectedItem, setSelectedItem] = useState("All");
  const [searchValue, setSearchValue] = useState("");

  const dropdownItems = ["All", "Option1", "Option2", "Option3"];

  const handleSearch = () => {
    console.log("Searching for:", searchValue);
  };

  return (
    <div className="search">
      <div className="search-fields">
        <div className="search-fields">
          <div className="search-select">
            <div onClick={() => setSelectedItem(dropdownItems[0])} className="all">{selectedItem}</div>
            <img className="icondropdown" alt="" src="/icondropdown.svg" />
            <div className="dropdown-items">
              {dropdownItems.map(item => (
                <div key={item} onClick={() => setSelectedItem(item)}>
                  {item}
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className="search1">
          <img className="iconsearch" alt="" src="/iconsearch.svg" />
          <input
            value={searchValue}
            onChange={e => setSearchValue(e.target.value)}
            placeholder="Search"
            style={{flex: 1}}
            className="text7"
          />
        </div>
      </div>
      <div className="secondary-button" onClick={handleSearch}>
        <div className="text8">Search</div>
      </div>
    </div>
  );
};

export default SearchComponent;
