import React, { useState } from 'react';

const Menu = () => {
  const [menuList, setMenuList] = useState([
    { id: 1, title: '메뉴 1', link: '/test', date: '2024-01-01' },
    // 기존 메뉴 항목 추가
  ]);

  const [newMenuTitle, setNewMenuTitle] = useState('');
  const [newMenuLink, setNewMenuLink] = useState('');

  const addRecord = () => {
    const newMenu = {
      id: menuList.length + 1,
      title: newMenuTitle,
      link: newMenuLink,
      date: new Date().toLocaleDateString(),
    };

    setMenuList([newMenu, ...menuList]);
    setNewMenuTitle('');
    setNewMenuLink('');
  };

  return (
    <div>
      <header>
        <h1>메뉴 관리</h1>
      </header>

      <main>
        <table>
          <thead>
            <tr>
              <th style={{ width: '5%' }}>순번</th>
              <th style={{ width: '20%' }}>메뉴명</th>
              <th style={{ width: '30%' }}>링크주소</th>
              <th style={{ width: '15%' }}>등록일자</th>
              <th style={{ width: '5%' }}>작업</th>
            </tr>
          </thead>
          <tbody>
            {menuList.map((menu) => (
              <tr key={menu.id}>
                <td style={{ textAlign: 'center' }}>{menu.id}</td>
                <td style={{ textAlign: 'left' }}>{menu.title}</td>
                <td style={{ textAlign: 'left' }}>{menu.link}</td>
                <td style={{ textAlign: 'center' }}>{menu.date}</td>
                <td className="action-buttons">
                  <button>수정</button>
                  <button>삭제</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        <div>
          <input
            type="text"
            name="menuTitle"
            placeholder="새로운 메뉴"
            value={newMenuTitle}
            onChange={(e) => setNewMenuTitle(e.target.value)}
          />
          <input
            type="text"
            name="menuLink"
            placeholder="url을 입력하세요"
            value={newMenuLink}
            onChange={(e) => setNewMenuLink(e.target.value)}
          />
          <button className="add-button" onClick={addRecord}>
            메뉴명 추가
          </button>
        </div>
      </main>
    </div>
  );
};

export default Menu;
