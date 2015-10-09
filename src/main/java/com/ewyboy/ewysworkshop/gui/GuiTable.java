package com.ewyboy.ewysworkshop.gui;

import com.ewyboy.ewysworkshop.gui.container.ContainerTable;
import com.ewyboy.ewysworkshop.gui.container.slot.SlotBase;
import com.ewyboy.ewysworkshop.item.Upgrade;
import com.ewyboy.ewysworkshop.network.PacketHandler;
import com.ewyboy.ewysworkshop.network.PacketId;
import com.ewyboy.ewysworkshop.network.data.DataType;
import com.ewyboy.ewysworkshop.page.Page;
import com.ewyboy.ewysworkshop.tileentity.TileEntityTable;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiTable extends GuiBase {

    private TileEntityTable table;
    private List<SlotBase> slots;

    public GuiTable(TileEntityTable table, EntityPlayer player) {
        super(new ContainerTable(table, player));
        xSize = 256;
        ySize = 256;
        slots = new ArrayList<SlotBase>();
        for (Object obj : inventorySlots.inventorySlots){
            SlotBase slot = (SlotBase)obj;
            slots.add(slot);

            slot.updateClient(slot.isVisible());
        }
        this.table = table;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int mX, int mY) {
        GL11.glPushMatrix();
            GL11.glTranslatef(guiLeft, guiTop, 0);
            mX -= guiLeft;
            mY -= guiTop;

            mc.getTextureManager().bindTexture(BACKGROUND);
            GL11.glColor3ub((byte)255, (byte)255, (byte)255);
            drawTexturedModalRect(0, 0, 0, 0, xSize, ySize);

            drawSlots();
            if (table.getMenu() == null) {
                drawPageHeaders(mX, mY);
                drawPower(mX, mY);
                table.getSelectedPage().draw(this, mX, mY);
            }else{
                table.getMenu().draw(this, mX, mY);
            }
        GL11.glPopMatrix();
    }

    @Override
    protected void mouseClicked(int mX, int mY, int button) {
        super.mouseClicked(mX, mY, button);
        mX -= guiLeft;
        mY -= guiTop;

        if (table.getMenu() == null) {
            clickPageHeader(mX, mY);
            table.getSelectedPage().onClick(this, mX, mY, button);
        }else{
            table.getMenu().onClick(this, mX, mY);
        }
    }

    @Override
    protected void mouseMovedOrUp(int mX, int mY, int button) {
        super.mouseMovedOrUp(mX, mY, button);
        mX -= guiLeft;
        mY -= guiTop;


        if (table.getMenu() == null) {
            table.getSelectedPage().onRelease(this, mX, mY, button);
        }else{
            table.getMenu().onRelease(this, mX, mY);
        }

    }

    @Override
    protected void keyTyped(char c, int k) {
        if (table.getMenu() == null) {
            super.keyTyped(c, k);
        }else{
            if (k == 1) {
                this.mc.thePlayer.closeScreen();
            }else{
                table.getMenu().onKeyStroke(this, c, k);
            }
        }
    }

    private static final int HEADER_SRC_X = 0;
    private static final int HEADER_SRC_Y = 0;
    private static final int HEADER_FULL_WIDTH = 42;
    private static final int HEADER_WIDTH = 38;
    private static final int HEADER_HEIGHT = 17;
    private static final int HEADER_X = 3;
    private static final int HEADER_Y = 173;
    private static final int HEADER_TEXT_Y = 7;

    private void drawPageHeaders(int mX, int mY) {
        for (int i = 0; i < table.getPages().size(); i++) {
            Page page = table.getPages().get(i);

            boolean selected = page.equals(table.getSelectedPage());
            int srcY = selected ? HEADER_SRC_Y + HEADER_HEIGHT : HEADER_SRC_Y;
            int y = HEADER_Y + HEADER_HEIGHT * i;


            boolean hover = inBounds(HEADER_X, y, HEADER_FULL_WIDTH, HEADER_HEIGHT, mX, mY);
            int width = hover ? HEADER_FULL_WIDTH : HEADER_WIDTH;
            int offset = HEADER_FULL_WIDTH - width;

            prepare();
            drawRect(HEADER_X, y, HEADER_SRC_X + offset, srcY, width, HEADER_HEIGHT);

            int invertedOffset = (HEADER_FULL_WIDTH - HEADER_WIDTH) - offset;
            drawCenteredString(page.getName(), HEADER_X + invertedOffset, y + HEADER_TEXT_Y, HEADER_WIDTH, 0.7F, 0x404040);
        }
    }

    private void clickPageHeader(int mX, int mY) {
        for (int i = 0; i < table.getPages().size(); i++) {
            Page page = table.getPages().get(i);
            int y = HEADER_Y + HEADER_HEIGHT * i;
            if (inBounds(HEADER_X, y, HEADER_FULL_WIDTH, HEADER_HEIGHT, mX, mY)) {
                table.setSelectedPage(page);
                table.updateServer(DataType.PAGE);
                break;
            }
        }
    }

    private static final int SLOT_SRC_X = 42;
    private static final int SLOT_SRC_Y = 0;
    private static final int SLOT_SIZE = 18;
    private static final int SLOT_OFFSET = -1;
    private static final int SLOT_BIG_SIZE = 26;
    private static final int SLOT_BIG_OFFSET = SLOT_OFFSET - (SLOT_BIG_SIZE - SLOT_SIZE) / 2;
    private void drawSlots() {
        prepare();
        for (SlotBase slot : slots) {
            boolean visible = slot.isVisible();
            slot.updateClient(visible);
            if (visible) {
                boolean isBig = slot.isBig();
                int srcY = isBig ? SLOT_SIZE + SLOT_SRC_Y : SLOT_SRC_Y;
                int size = isBig ? SLOT_BIG_SIZE : SLOT_SIZE;
                int offset = isBig ? SLOT_BIG_OFFSET : SLOT_OFFSET;

                drawRect(slot.getX() + offset, slot.getY() + offset, SLOT_SRC_X + slot.getTextureIndex(this) * size, srcY, size, size);
            }
        }
    }

    private static final int POWER_X = 225;
    private static final int POWER_Y = 173;
    private static final int POWER_WIDTH = 18;
    private static final int POWER_HEIGHT = 50;
    private static final int POWER_INNER_WIDTH = 16;
    private static final int POWER_INNER_HEIGHT = 48;

    private static final int POWER_INNER_SRC_X = 0;
    private static final int POWER_INNER_SRC_Y = 64;
    private static final int POWER_SRC_X = 32;
    private static final int POWER_SRC_Y = 62;

    private static final int POWER_INNER_OFFSET_X = (POWER_WIDTH - POWER_INNER_WIDTH) / 2;
    private static final int POWER_INNER_OFFSET_Y = (POWER_HEIGHT - POWER_INNER_HEIGHT) / 2;

    private void drawPower(int mX, int mY) {
        prepare();
        drawRect(POWER_X + POWER_INNER_OFFSET_X, POWER_Y + POWER_INNER_OFFSET_Y, POWER_INNER_SRC_X + POWER_INNER_WIDTH, POWER_INNER_SRC_Y, POWER_INNER_WIDTH, POWER_INNER_HEIGHT);

        int height = POWER_INNER_HEIGHT * table.getPower() / TileEntityTable.MAX_POWER;
        int offset = POWER_INNER_HEIGHT - height;
        drawRect(POWER_X + POWER_INNER_OFFSET_X, POWER_Y + POWER_INNER_OFFSET_Y + offset, POWER_INNER_SRC_X, POWER_INNER_SRC_Y + offset, POWER_INNER_WIDTH, height);
        drawRect(POWER_X, POWER_Y + POWER_INNER_OFFSET_Y + offset - 1, POWER_SRC_X, POWER_SRC_Y - 1, POWER_WIDTH, 1);

        int srcX = POWER_SRC_X;
        boolean hover = inBounds(POWER_X, POWER_Y, POWER_WIDTH, POWER_HEIGHT, mX, mY);
        if (hover) {
            srcX += POWER_WIDTH;
        }
        drawRect(POWER_X, POWER_Y, srcX, POWER_SRC_Y, POWER_WIDTH, POWER_HEIGHT);

        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("power", table.getPower());
        int power = tag.getInteger("power");
        EnumChatFormatting color;
        if (power <= ((table.MAX_POWER * 12.5) / 100)) {
            color = EnumChatFormatting.DARK_RED;
        } else if (power <= ((table.MAX_POWER * 25) / 100)) {
            color = EnumChatFormatting.RED;
        } else if (power <= ((table.MAX_POWER * 37.5) / 100)) {
            color = EnumChatFormatting.GOLD;
        } else if (power <= ((table.MAX_POWER * 50) / 100)) {
            color = EnumChatFormatting.YELLOW;
        } else if (power <= ((table.MAX_POWER * 62.5) / 100)) {
            color = EnumChatFormatting.DARK_GREEN;
        } else if (power <= ((table.MAX_POWER * 75) / 100)) {
            color = EnumChatFormatting.GREEN;
        } else if (power <= ((table.MAX_POWER * 87.5) / 100)) {
            color = EnumChatFormatting.DARK_AQUA;
        } else {
            color = EnumChatFormatting.AQUA;
        }

        if (hover) {
            String str = color + "Power: " + formatNumber(table.getPower()) + "/" + formatNumber(TileEntityTable.MAX_POWER);
            if (table.getLava() > 0 && table.getUpgradePage().hasGlobalUpgrade(Upgrade.LAVA)) {
                str += "\n" + EnumChatFormatting.GOLD + "Lava: " + formatNumber(table.getLava()) + "/" + formatNumber(TileEntityTable.MAX_LAVA);
            }
            if (table.getUpgradePage().hasGlobalUpgrade(Upgrade.SOLAR)) {
                str += "\n" + EnumChatFormatting.YELLOW + "Solar panel: " + (table.isLitAndCanSeeTheSky() ? "Lit" : EnumChatFormatting.GRAY + "Dark");
            }
            drawMouseOver(str);
        }
    }

    private String formatNumber(int number) {
        return String.format("%,d", number).replace((char)160,(char)32);
    }

    private boolean closed;
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        PacketHandler.sendToServer(PacketHandler.getWriter(table, PacketId.CLOSE));
        closed = true;
    }

    @Override
    public void setWorldAndResolution(Minecraft minecraft, int width, int height) {
        super.setWorldAndResolution(minecraft, width, height);

        if (closed) {
            PacketHandler.sendToServer(PacketHandler.getWriter(table, PacketId.RE_OPEN));
            closed = false;
        }
    }

    public TileEntityTable getTable() {
        return table;
    }
}
